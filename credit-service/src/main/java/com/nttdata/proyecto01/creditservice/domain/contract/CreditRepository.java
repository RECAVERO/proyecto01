package com.nttdata.proyecto01.creditservice.domain.contract;

import com.nttdata.proyecto01.creditservice.domain.model.CreditDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditRepository {
    Flux<CreditDTO> getListCredit();
    Mono<CreditDTO> getCreditById(String id);
    Mono<CreditDTO> saveCredit(Mono<CreditDTO> creditDTOMono);

    Mono<CreditDTO> updateCredit(Mono<CreditDTO> creditDTOMono, String idclient,String idproduct);
    Mono<Void> deleteCreditById(String id);

    Flux<CreditDTO> getListByIdClient(String idClient);
    Flux<CreditDTO> getListCreditByIdClientAndIdProduct(String idClient,String idProduct);
    Mono<CreditDTO> getListCreditByIdClientAndIdTypeAndIdProduct( String idClient, String idType, String idProduct);



    //Flux<CreditDTO> getCreditByIdClient(String idclient);


}
