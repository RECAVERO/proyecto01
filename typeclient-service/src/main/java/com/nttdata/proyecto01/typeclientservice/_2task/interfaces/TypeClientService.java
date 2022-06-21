package com.nttdata.proyecto01.typeclientservice._2task.interfaces;

import com.nttdata.proyecto01.typeclientservice._3domain.model.TypeClientDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TypeClientService {
    Flux<TypeClientDTO> getListTypeClient();
    Mono<TypeClientDTO> getTypeClientById(String id);
    Mono<TypeClientDTO> saveTypeClient(Mono<TypeClientDTO> typeClientDtoMono);
    Mono<TypeClientDTO> updateTypeClient(Mono<TypeClientDTO> typeClientDtoMono, String id);
    Mono<Void> deleteTypeClientById(String id);

    //Flux<TypeClientDTO> getTypeClientByIdClient(String idclient);
}
