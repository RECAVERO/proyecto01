package com.nttdata.proyecto01.creditservice.infraestructure.data.mongodb;

import com.nttdata.proyecto01.creditservice.domain.model.CreditDTO;
import com.nttdata.proyecto01.creditservice.infraestructure.data.document.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditRepositoryMongoDB extends ReactiveMongoRepository<Credit,String> {
    Flux<CreditDTO> findByIdClient(String idClient);

    Flux<CreditDTO> findByIdClientAndIdProduct(String idClient, String idProduct);

    Mono<CreditDTO> findByIdClientAndIdTypeAndIdProduct(String idClient, String idType, String idProduct);

}
