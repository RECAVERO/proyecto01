package com.nttdata.proyecto01.clientservice.infrastructure.data.repositories;

import com.nttdata.proyecto01.clientservice.domain.documents.ClientDTO;
import com.nttdata.proyecto01.clientservice.infrastructure.data.documents.Client;
import com.nttdata.proyecto01.clientservice.infrastructure.data.interfaces.ClientRepository;
import com.nttdata.proyecto01.clientservice.infrastructure.data.mongodb.ClientReactiveMongoRepository;
import org.reactivestreams.Publisher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;
@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientReactiveMongoRepository _clientReactiveMongoRepository;
    public ClientRepositoryImpl(ClientReactiveMongoRepository clientReactiveMongoRepository){
        this._clientReactiveMongoRepository=clientReactiveMongoRepository;
    }
    @Override
    public Flux<Client> findAll() {
        return this._clientReactiveMongoRepository.findAll();
    }

    @Override
    public Mono<Client> save(Client client) {
        return this._clientReactiveMongoRepository.save(client);
    }

    @Override
    public Mono<Client> findById(String id) {
        return this._clientReactiveMongoRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return this._clientReactiveMongoRepository.deleteById(id);
    }
}
