package com.nttdata.proyecto01.typeclientservice.infraestructure.data.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.nttdata.proyecto01.typeclientservice.infraestructure.data.document.typeClient;


public interface typeClientRepository extends ReactiveMongoRepository<typeClient,String> {
}
