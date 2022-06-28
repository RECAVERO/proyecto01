package com.nttdata.proyecto01.creditservice.application.rest;

import com.nttdata.proyecto01.creditservice.btask.interfaces.CreditService;
import com.nttdata.proyecto01.creditservice.domain.model.CreditDTO;
import com.nttdata.proyecto01.creditservice.domain.model.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/credit")
public class CreditController {
  private final CreditService creditService;

  public CreditController(CreditService creditService) {
    this.creditService = creditService;
  }

  @GetMapping
  public Flux<CreditDTO> getListCredit() {
    return this.creditService.getListCredit();
  }

  @GetMapping("/{id}")
  public Mono<CreditDTO> getCreditById(@PathVariable String id) {
    return this.creditService.getCreditById(id);
  }

  @PostMapping
  public Mono<Map<String, Object>> saveCredit(@RequestBody Mono<CreditDTO> creditDto) {
    Map<String, Object> result = new HashMap<>();
    return creditDto.flatMap(credit->{
      if(credit.getIdType().equals("typ01")){
        return this.creditService.getListCreditAll(credit.getIdClient(), credit.getIdType(), credit.getIdProduct(), credit.getNumberCuent())
            .flatMap(c -> {
              if(c.getId()==null){
                return this.creditService.getListCreditByIdClientAndIdTypeAndIdProduct(credit.getIdClient(), credit.getIdType(), credit.getIdProduct())
                    .flatMap(product->{
                      if(product.getId()==null){
                        return this.creditService.saveCredit(Mono.just(credit)).flatMap(creditSave->{
                          ResponseDto res=new ResponseDto();
                          res.setStatus(HttpStatus.CREATED.toString());
                          res.setMsg("Se Registro Correctamente");
                          res.setCreditDto(creditSave);
                          result.put("Data",res);
                          return Mono.just(result);
                        });
                      }else{
                        ResponseDto res=new ResponseDto();
                        res.setStatus(HttpStatus.NOT_FOUND.toString());
                        res.setMsg("No se Registro; porque ya tiene una cuenta asociada a " + product.getIdProduct());
                        result.put("Data",res);
                        return Mono.just(result);
                      }
                    });
              }else{
                ResponseDto res=new ResponseDto();
                res.setStatus(HttpStatus.NOT_FOUND.toString());
                res.setMsg("No se Registro; porque ya tiene un numero de cuenta " + c.getNumberCuent());
                result.put("Data",res);
                return Mono.just(result);
              }

            });
      }else if(credit.getIdType().equals("typ02")){
        if(credit.getIdProduct().equals("pro02")){
          return this.creditService.getListCreditAll(credit.getIdClient(), credit.getIdType(), credit.getIdProduct(), credit.getNumberCuent())
              .flatMap(k->{
                    if(k.getId()==null){
                      return this.creditService.saveCredit(Mono.just(credit)).flatMap(creditSave->{
                        ResponseDto res=new ResponseDto();
                        res.setStatus(HttpStatus.CREATED.toString());
                        res.setMsg("Se Registro Correctamente");
                        res.setCreditDto(creditSave);
                        result.put("Data",res);
                        return Mono.just(result);
                      });
                    }else{
                      ResponseDto res=new ResponseDto();
                      res.setStatus(HttpStatus.NOT_FOUND.toString());
                      res.setMsg("Ya cuenta con un producto ");
                      result.put("Data",res);
                      return Mono.just(result);
                    }
            });
        }else {
          ResponseDto res=new ResponseDto();
          res.setStatus(HttpStatus.NOT_FOUND.toString());
          res.setMsg("Type de cliente no puede crear estos productos");
          result.put("Data",res);
          return Mono.just(result);
        }

      }else{
        ResponseDto res=new ResponseDto();
        res.setStatus(HttpStatus.NOT_FOUND.toString());
        res.setMsg("No Existe Tipo de Cliente");
        result.put("Data",res);
        return Mono.just(result);
      }

    });

    //return this.creditService.saveCredit(creditDto);
  }

  @PutMapping("/{id}")
  public Mono<CreditDTO> updateCredit(@RequestBody Mono<CreditDTO> creditDto,
                                      @PathVariable String id) {
    return this.creditService.updateCredit(creditDto, id);
  }
  @PostMapping("/deposit")
  public Mono<CreditDTO> updateCreditDeposit(@RequestBody Mono<CreditDTO> creditDto) {
    return creditDto.flatMap(credit -> {
      return this.creditService.getCreditById(credit.getId()).flatMap(c->{
          CreditDTO cre=new CreditDTO();
          cre.setId(credit.getId());
          cre.setIdClient(credit.getIdClient());
          cre.setIdType(credit.getIdType());
          cre.setIdProduct(credit.getIdProduct());
          cre.setNumberCuent(credit.getNumberCuent());
          cre.setBalance(c.getBalance() + credit.getBalance());
          System.out.println(c.getBalance() + credit.getBalance());
        return this.creditService.updateCredit(Mono.just(cre), credit.getId());
      });
    });
  }

  @PostMapping("/withdrawal")
  public Mono<CreditDTO> updateCreditWithdrawal(@RequestBody Mono<CreditDTO> creditDto) {
    return creditDto.flatMap(credit -> {
      return this.creditService.getCreditById(credit.getId()).flatMap(c->{
        CreditDTO cre=new CreditDTO();
        cre.setId(credit.getId());
        cre.setIdClient(credit.getIdClient());
        cre.setIdType(credit.getIdType());
        cre.setIdProduct(credit.getIdProduct());
        cre.setNumberCuent(credit.getNumberCuent());
        cre.setBalance(c.getBalance() - credit.getBalance());
        return this.creditService.updateCredit(Mono.just(cre), credit.getId());
      });
    });
  }

  @DeleteMapping("/{id}")
  public Mono<Void> deleteCreditById(@PathVariable("id") String id) {
    return this.creditService.deleteCreditById(id);
  }

  @GetMapping("/operation/{id}")
  public Flux<CreditDTO> getListByIdClient(@PathVariable("id") String id) {
    return this.creditService.getListByIdClient(id);
  }

  @GetMapping("/products/{idclient}/{idproduct}")
  public Flux<CreditDTO> getListByIdClient(@PathVariable("idclient") String idclient,
                                           @PathVariable("idproduct") String idproduct) {
    return this.creditService.getListCreditByIdClientAndIdProduct(idclient, idproduct);
  }

  @GetMapping("/products/staff/{idClient}/{idType}/{idProduct}")
  public Mono<CreditDTO> getListByStaff(@PathVariable String idClient,
                                           @PathVariable String idType,
                                           @PathVariable String idProduct) {
    return this.creditService.getListCreditByIdClientAndIdTypeAndIdProduct(idClient, idType, idProduct);
  }

  @GetMapping("/products/{idClient}/{idType}/{idProduct}/{numberCuent}")
  public Mono<CreditDTO> getListByIdClient(@PathVariable String idClient,
                                           @PathVariable String idType,
                                           @PathVariable String idProduct,
                                           @PathVariable String numberCuent) {
    return this.creditService.getListCreditAll(idClient, idType, idProduct, numberCuent);
  }

}
