package com.nttdata.proyecto01.creditservice._2task.interfaces;

import com.nttdata.proyecto01.creditservice._3domain.model.CreditDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {
    Flux<CreditDTO> getListCredit();
    Mono<CreditDTO> getCreditById(String id);
    Mono<CreditDTO> saveCredit(Mono<CreditDTO> creditDTOMono);

    Mono<CreditDTO> updateCredit(Mono<CreditDTO> creditDTOMono, String id);
    Mono<Void> deleteCreditById(String id);
    Flux<CreditDTO> getCreditByIdClient(String idclient);
}
