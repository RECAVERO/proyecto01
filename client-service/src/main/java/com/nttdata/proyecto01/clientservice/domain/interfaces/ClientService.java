package com.nttdata.proyecto01.clientservice.domain.interfaces;

import com.nttdata.proyecto01.clientservice.domain.documents.ClientDTO;
import com.nttdata.proyecto01.clientservice.domain.documents.TypeClientDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public interface ClientService {
    Flux<ClientDTO> findAllClient();
    Mono<ClientDTO> saveClient(Mono<ClientDTO> clientDTO);
    Mono<ClientDTO> findByIdClient(String id);
    Mono<Void> deleteByIdClient(String id);
    Mono<ClientDTO> updateClient(Mono<ClientDTO> clientDTO,String id);

    Map<String,Object> getClientAndProducts(String idclient);
}
