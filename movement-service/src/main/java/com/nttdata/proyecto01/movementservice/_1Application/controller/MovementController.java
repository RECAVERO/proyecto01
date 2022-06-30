package com.nttdata.proyecto01.movementservice._1Application.controller;

import com.nttdata.proyecto01.movementservice._2Task.interfaces.MovementService;
import com.nttdata.proyecto01.movementservice._3Domain.model.CreditDTO;
import com.nttdata.proyecto01.movementservice._3Domain.model.MovementDTO;
import com.nttdata.proyecto01.movementservice._3Domain.model.ResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Movement")
public class MovementController {
  private final MovementService _movementService;

  public MovementController(MovementService movementService) {
    _movementService = movementService;
  }
  @GetMapping
  public Flux<MovementDTO> getListMovement(){
    return _movementService.getListMovement();
  }
  @GetMapping("/{id}")
  public Mono<MovementDTO> getMovementById(@PathVariable String id){
    return _movementService.getMovementById(id);
  }
  @GetMapping("/record/{idClient}/{numberCuent}")
  public Flux<MovementDTO> recordMovement(@PathVariable String idClient,
                                                  @PathVariable String numberCuent){
    return this._movementService.getListRecordMovement(idClient,numberCuent);
  }

  @CircuitBreaker(name="creditDeposit",fallbackMethod = "fallBackDepositMovement")
  @PostMapping("/deposit")
  public Mono<Map<String, Object>> depositMovement(@RequestBody Mono<MovementDTO> movementDto){
    Map<String, Object> result=new HashMap<>();
    return movementDto.flatMap(movement->{
      return _movementService.getListCredit(movement.getIdClient(),movement.getIdType(),movement.getIdProduct(),movement.getNumberCuent()).flatMap(c->{
        ResponseDto responseDto = new ResponseDto();
          if(c.getId() == null){
            responseDto.setStatus(HttpStatus.NOT_FOUND.toString());
            responseDto.setMsg("No Hay registros con es criterio de busqueda");
            result.put("Data",responseDto);
            return Mono.just(result);
          }else{
            CreditDTO cred=new CreditDTO();
            cred.setId(c.getId());
            cred.setIdClient(c.getIdClient());
            cred.setIdType(c.getIdType());
            cred.setIdProduct(c.getIdProduct());
            cred.setNumberCuent(c.getNumberCuent());
            cred.setBalance(movement.getAmount());
            return _movementService.updateCreditDeposit(Mono.just(cred)).flatMap(credit->{
              return _movementService.saveMovement(Mono.just(movement)).flatMap(h->{
                responseDto.setStatus(HttpStatus.CREATED.toString());
                responseDto.setMsg("Se Registro Correctamente");
                responseDto.setMovementDto(h);
                result.put("Data",responseDto);
                return Mono.just(result);
              });
            });

          }
      });
    });
  }

  @CircuitBreaker(name="creditWithdrawal",fallbackMethod = "fallBackWithdrawalMovement")
  @PostMapping("/withdrawal")
  public Mono<Map<String, Object>> withdrawalMovement(@RequestBody Mono<MovementDTO> movementDto){
    Map<String, Object> result=new HashMap<>();
    return movementDto.flatMap(movement->{
      return _movementService.getListCredit(movement.getIdClient(),movement.getIdType(),movement.getIdProduct(),movement.getNumberCuent()).flatMap(c->{
        ResponseDto responseDto = new ResponseDto();
        if(c.getId() == null){
          responseDto.setStatus(HttpStatus.NOT_FOUND.toString());
          responseDto.setMsg("No Hay registros con es criterio de busqueda");
          result.put("Data",responseDto);
          return Mono.just(result);
        }else{
          CreditDTO cred=new CreditDTO();
          cred.setId(c.getId());
          cred.setIdClient(c.getIdClient());
          cred.setIdType(c.getIdType());
          cred.setIdProduct(c.getIdProduct());
          cred.setNumberCuent(c.getNumberCuent());
          cred.setBalance(movement.getAmount());
          return _movementService.updateCreditWithdrawal(Mono.just(cred)).flatMap(credit->{
            return _movementService.saveMovement(Mono.just(movement)).flatMap(h->{
              responseDto.setStatus(HttpStatus.CREATED.toString());
              responseDto.setMsg("Se Registro Correctamente");
              responseDto.setMovementDto(h);
              result.put("Data",responseDto);
              return Mono.just(result);
            });
          });

        }
      });
    });
  }

  @PutMapping("/{id}")
  public Mono<MovementDTO> updateMovement(@RequestBody Mono<MovementDTO> movementDTOMono,@PathVariable String id){
    return _movementService.updateMovement(movementDTOMono,id);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> deleteMovementById(@PathVariable String id){
    return _movementService.deleteMovementById(id);
  }

  public Mono<Map<String, Object>> fallBackDepositMovement(@RequestBody Mono<MovementDTO> movementDto,RuntimeException e){
    Map<String, Object> result=new HashMap<>();
    ResponseDto responseDto = new ResponseDto();
    responseDto.setStatus(HttpStatus.NOT_FOUND.toString());
    responseDto.setMsg("No se puede hacer depositos; porque el servicio no esta disponible por el momento");
    result.put("Datasource",responseDto);
    return Mono.just(result);
  }

  public Mono<Map<String, Object>> fallBackWithdrawalMovement(@RequestBody Mono<MovementDTO> movementDto,RuntimeException e){
    Map<String, Object> result=new HashMap<>();
    ResponseDto responseDto = new ResponseDto();
    responseDto.setStatus(HttpStatus.NOT_FOUND.toString());
    responseDto.setMsg("No se puede hacer retiros; porque el servicio no esta disponible por el momento");
    result.put("Datasource",responseDto);
    return Mono.just(result);
  }


}
