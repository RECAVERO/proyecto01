package com.nttdata.proyecto01.movementservice._1Application.controller;

import com.nttdata.proyecto01.movementservice._2Task.interfaces.MovementService;
import com.nttdata.proyecto01.movementservice._3Domain.model.CreditDTO;
import com.nttdata.proyecto01.movementservice._3Domain.model.MovementDTO;
import com.nttdata.proyecto01.movementservice._3Domain.model.ResponseDto;
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
  /*
  @PostMapping("/withdrawal")
  public Mono<MovementDTO> withdrawalMovement(@RequestBody Mono<MovementDTO> movementDTOMono){
    return _movementService.saveMovement(movementDTOMono);
  }
   */
  @PutMapping("/{id}")
  public Mono<MovementDTO> updateMovement(@RequestBody Mono<MovementDTO> movementDTOMono,@PathVariable String id){
    return _movementService.updateMovement(movementDTOMono,id);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> deleteMovementById(@PathVariable String id){
    return _movementService.deleteMovementById(id);
  }
  /*@GetMapping("/operation/{idclient}/{idtype}/{idproduct}")
  public Mono<CreditDTO> getListProducts(@PathVariable String idclient,@PathVariable String idtype,@PathVariable String idproduct){
    return _movementService.getListCredit(idclient,idtype,idproduct);
  }

   */


}
