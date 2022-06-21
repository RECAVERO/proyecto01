package com.nttdata.proyecto01.creditservice._2task.interfaces;

import com.nttdata.proyecto01.creditservice._3domain.model.CreditDTO;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {
    Flux<CreditDTO> getListCredit();
    Mono<CreditDTO> getCreditById(String id);
    Mono<CreditDTO> saveCredit(Mono<CreditDTO> creditDTOMono);

    Mono<CreditDTO> updateCredit(Mono<CreditDTO> creditDTOMono, String idclient,String idproduct);
    Mono<Void> deleteCreditById(String id);
    Flux<CreditDTO> getListByIdClient(String idClient);
    Flux<CreditDTO> getListCreditByIdClientAndIdProduct(String idClient,String idProduct);
    //Mono<CreditDTO> getListCreditByIdClientAndIdTypeAndIdProduct(String idClient,String idType,String idProduct);
    Mono<CreditDTO> getListCreditByIdClientAndIdTypeAndIdProduct( String idClient, String idType, String idProduct);
}
