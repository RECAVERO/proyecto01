package com.nttdata.proyecto01.typeclientservice.infraestructure.data.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.nttdata.proyecto01.typeclientservice.infraestructure.data.document.TypeClient;


public interface TypeClientRepositoryMongoDB extends ReactiveMongoRepository<TypeClient,String> {
    //Flux<TypeClientDTO> findByIdClient(String id);
}
