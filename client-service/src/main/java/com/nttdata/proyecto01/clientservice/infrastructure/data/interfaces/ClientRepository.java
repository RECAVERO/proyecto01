package com.nttdata.proyecto01.clientservice.infrastructure.data.interfaces;

import com.nttdata.proyecto01.clientservice.domain.documents.ClientDTO;
import com.nttdata.proyecto01.clientservice.infrastructure.data.documents.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientRepository {
    Flux<ClientDTO> findAllClient();
    Mono<ClientDTO> saveClient(Mono<ClientDTO> clientDTO);
    Mono<ClientDTO> findByIdClient(String id);

    Mono<ClientDTO> updateClient(Mono<ClientDTO> clientDTO,String id);
    Mono<Void> deleteByIdClient(String id);

}
