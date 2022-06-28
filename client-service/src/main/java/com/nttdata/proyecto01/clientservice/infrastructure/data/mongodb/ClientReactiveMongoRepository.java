package com.nttdata.proyecto01.clientservice.infrastructure.data.mongodb;

import com.nttdata.proyecto01.clientservice.infrastructure.data.documents.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Clase que tiene los metodos que construyen los query para extraer los datos de la base de datos.
 */

@Repository
public interface ClientReactiveMongoRepository extends ReactiveMongoRepository<Client, String> {

}
