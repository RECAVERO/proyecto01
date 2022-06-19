package com.nttdata.proyecto01.typeclientservice._3domain.service;

import com.nttdata.proyecto01.typeclientservice._2task.interfaces.TypeClientService;
import com.nttdata.proyecto01.typeclientservice._3domain.contract.TypeClientRepository;
import com.nttdata.proyecto01.typeclientservice._3domain.model.TypeClientDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class TypeClientServiceImpl implements TypeClientService {
    private final TypeClientRepository _typeClientRepository;

    public TypeClientServiceImpl(TypeClientRepository typeClientRepository) {
        this._typeClientRepository = typeClientRepository;
    }

    @Override
    public Flux<TypeClientDTO> getListTypeClient() {
        return this._typeClientRepository.getListTypeClient();
    }

    @Override
    public Mono<TypeClientDTO> getTypeClientById(String id) {
        return this._typeClientRepository.getTypeClientById(id);
    }

    @Override
    public Mono<TypeClientDTO> saveTypeClient(Mono<TypeClientDTO> typeClientDtoMono) {
        return this._typeClientRepository.saveTypeClient(typeClientDtoMono);
    }

    @Override
    public Mono<TypeClientDTO> updateTypeClient(Mono<TypeClientDTO> typeClientDtoMono, String id) {
        return this._typeClientRepository.updateTypeClient(typeClientDtoMono,id);
    }

    @Override
    public Mono<Void> deleteTypeClientById(String id) {
        return this._typeClientRepository.deleteTypeClientById(id);
    }

    @Override
    public Flux<TypeClientDTO> getTypeClientByIdClient(String idclient) {
        return this._typeClientRepository.getTypeClientByIdClient(idclient);
    }
}
