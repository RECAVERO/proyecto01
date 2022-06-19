package com.nttdata.proyecto01.clientservice.infrastructure.data.mongodb;

import com.nttdata.proyecto01.clientservice.domain.documents.ClientDTO;
import com.nttdata.proyecto01.clientservice.infrastructure.data.documents.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClientReactiveMongoRepository extends ReactiveMongoRepository<Client,String> {
   // Mono<ClientDTO> save(Mono<ClientDTO> clientDtoMono);
}
