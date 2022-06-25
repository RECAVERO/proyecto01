package com.nttdata.proyecto01.typeclientservice.infraestructure.data.repository;

import com.nttdata.proyecto01.typeclientservice.domain.contract.TypeClientRepository;
import com.nttdata.proyecto01.typeclientservice.domain.model.TypeDto;
import com.nttdata.proyecto01.typeclientservice.infraestructure.data.mongodb.TypeClientRepositoryMongoDB;
import com.nttdata.proyecto01.typeclientservice.utils.convert.Convert;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public class TypeClientRepositoryImpl implements TypeClientRepository {
    private final TypeClientRepositoryMongoDB _typeClientRepositoryMongoDB;

    public TypeClientRepositoryImpl(TypeClientRepositoryMongoDB typeClientRepositoryMongoDB) {
        this._typeClientRepositoryMongoDB = typeClientRepositoryMongoDB;
    }

    @Override
    public Flux<TypeDto> getListTypeClient() {
        return this._typeClientRepositoryMongoDB.findAll().map(Convert::entityToDto);
    }

    @Override
    public Mono<TypeDto> getTypeClientById(String id) {
        return this._typeClientRepositoryMongoDB.findById(id).map(Convert::entityToDto);
    }

    @Override
    public Mono<TypeDto> saveTypeClient(Mono<TypeDto> typeClientDtoMono) {
        return typeClientDtoMono.map(Convert::DtoToEntity)
                .flatMap(_typeClientRepositoryMongoDB::insert)
                .map(Convert::entityToDto);
    }

    @Override
    public Mono<TypeDto> updateTypeClient(Mono<TypeDto> typeClientDtoMono, String id) {
        return _typeClientRepositoryMongoDB.findById(id)
                .flatMap(p -> typeClientDtoMono.map(Convert::DtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(_typeClientRepositoryMongoDB::save)
                .map(Convert::entityToDto);
    }

    @Override
    public Mono<Void> deleteTypeClientById(String id) {
        return _typeClientRepositoryMongoDB.deleteById(id);
    }

   /* @Override
    public Flux<TypeClientDTO> getTypeClientByIdClient(String idclient) {
        return this._typeClientRepositoryMongoDB.findByIdClient(idclient);
    }

    */
}
