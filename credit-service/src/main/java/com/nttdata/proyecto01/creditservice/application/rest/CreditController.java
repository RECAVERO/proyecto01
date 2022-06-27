package com.nttdata.proyecto01.creditservice.application.rest;

import com.nttdata.proyecto01.creditservice.btask.interfaces.CreditService;
import com.nttdata.proyecto01.creditservice.domain.model.CreditDTO;
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
  public Mono<CreditDTO> saveCredit(@RequestBody Mono<CreditDTO> creditDto) {
    return this.creditService.saveCredit(creditDto);
  }

  @PutMapping("/{id}")
  public Mono<CreditDTO> updateCredit(@RequestBody Mono<CreditDTO> creditDto,
                                      @PathVariable String id) {
    return this.creditService.updateCredit(creditDto, id);
  }
  @PostMapping("/deposit")
  public Mono<CreditDTO> updateCredit(@RequestBody Mono<CreditDTO> creditDto) {
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

  @GetMapping("/products/{idclient}/{idtype}/{idproduct}")
  public Mono<CreditDTO> getListByIdClient(@PathVariable String idclient,
                                           @PathVariable String idtype,
                                           @PathVariable String idproduct) {
    return this.creditService.getListCreditByIdClientAndIdTypeAndIdProduct(idclient, idtype, idproduct);
  }

  @GetMapping("/products/{idClient}/{idType}/{idProduct}/{numberCuent}")
  public Mono<CreditDTO> getListByIdClient(@PathVariable String idClient,
                                           @PathVariable String idType,
                                           @PathVariable String idProduct,
                                           @PathVariable String numberCuent) {
    return this.creditService.getListCreditAll(idClient, idType, idProduct, numberCuent);
  }

}
