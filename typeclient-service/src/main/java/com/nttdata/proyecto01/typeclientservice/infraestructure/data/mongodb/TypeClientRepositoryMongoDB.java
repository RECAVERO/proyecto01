package com.nttdata.proyecto01.typeclientservice.infraestructure.data.mongodb;

import com.nttdata.proyecto01.typeclientservice._3domain.model.TypeClientDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.nttdata.proyecto01.typeclientservice.infraestructure.data.document.TypeClient;
import reactor.core.publisher.Flux;


public interface TypeClientRepositoryMongoDB extends ReactiveMongoRepository<TypeClient,String> {
    //Flux<TypeClientDTO> findByIdClient(String id);
}
