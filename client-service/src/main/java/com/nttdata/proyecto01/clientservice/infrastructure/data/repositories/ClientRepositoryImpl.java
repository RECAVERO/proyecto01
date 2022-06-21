package com.nttdata.proyecto01.clientservice.infrastructure.data.repositories;

import com.nttdata.proyecto01.clientservice.domain.documents.ClientDTO;
import com.nttdata.proyecto01.clientservice.domain.documents.TypeClientDTO;
import com.nttdata.proyecto01.clientservice.infrastructure.data.interfaces.ClientRepository;
import com.nttdata.proyecto01.clientservice.infrastructure.data.mongodb.ClientReactiveMongoRepository;
import com.nttdata.proyecto01.clientservice.utils.Convert;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientReactiveMongoRepository _clientReactiveMongoRepository;
    public ClientRepositoryImpl(ClientReactiveMongoRepository clientReactiveMongoRepository){
        this._clientReactiveMongoRepository=clientReactiveMongoRepository;
    }
    @Override
    public Flux<ClientDTO> findAllClient() {
        return this._clientReactiveMongoRepository.findAll().map(Convert::entityToDto);
    }

    @Override
    public Mono<ClientDTO> saveClient(Mono<ClientDTO> clientDTO) {
        return clientDTO.map(Convert::DtoToEntity)
                .flatMap(_clientReactiveMongoRepository::insert)
                .map(Convert::entityToDto);
    }

    @Override
    public Mono<ClientDTO> findByIdClient(String id) {
        return this._clientReactiveMongoRepository.findById(id).map(Convert::entityToDto);
    }

    @Override
    public Mono<ClientDTO> updateClient(Mono<ClientDTO> clientDTO, String id) {
        return  _clientReactiveMongoRepository.findById(id)
                .flatMap(p->clientDTO.map(Convert::DtoToEntity)
                        .doOnNext(e->e.setId(id)))
                        .flatMap( _clientReactiveMongoRepository::save)
                        .map(Convert::entityToDto);
    }

    @Override
    public Mono<Void> deleteByIdClient(String id) {
        return this._clientReactiveMongoRepository.deleteById(id);
    }

}
