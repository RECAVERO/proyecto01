package com.nttdata.proyecto01.creditservice.btask.interfaces;

import com.nttdata.proyecto01.creditservice.domain.model.CreditDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {

  Flux<CreditDTO> getListCredit();

  Mono<CreditDTO> getCreditById(String id);

  Mono<CreditDTO> saveCredit(Mono<CreditDTO> creditDto);


  Mono<CreditDTO> updateCredit(Mono<CreditDTO> creditDto, String id);

  Mono<Void> deleteCreditById(String id);

  Flux<CreditDTO> getListByIdClient(String idClient);

  Flux<CreditDTO> getListCreditByIdClientAndIdProduct(String idClient, String idProduct);

  Mono<CreditDTO> getListCreditByIdClientAndIdTypeAndIdProduct(String idClient, String idType, String idProduct);

  Mono<CreditDTO> getListCreditAll(String idClient, String idType, String idProduct, String numberCuent);

}
