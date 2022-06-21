package com.nttdata.proyecto01.creditservice._3domain.contract;

import com.nttdata.proyecto01.creditservice._3domain.model.CreditDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditRepository {
    Flux<CreditDTO> getListCredit();
    Mono<CreditDTO> getCreditById(String id);
    Mono<CreditDTO> saveCredit(Mono<CreditDTO> creditDTOMono);

    Mono<CreditDTO> updateCredit(Mono<CreditDTO> creditDTOMono, String id);
    Mono<Void> deleteCreditById(String id);

    Flux<CreditDTO> getListByIdClient(String idClient);
    Flux<CreditDTO> getListCreditByIdClientAndIdProduct(String idClient,String idProduct);


    //Flux<CreditDTO> getCreditByIdClient(String idclient);


}
