package com.nttdata.proyecto01.clientservice.infrastructure.data.interfaces;

import com.nttdata.proyecto01.clientservice.domain.documents.ClientDTO;
import com.nttdata.proyecto01.clientservice.infrastructure.data.documents.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientRepository {
    Flux<Client> findAll();
    Mono<Client> save(Client client);
    Mono<Client> findById(String id);

    Mono<Void> deleteById(String id);

}
