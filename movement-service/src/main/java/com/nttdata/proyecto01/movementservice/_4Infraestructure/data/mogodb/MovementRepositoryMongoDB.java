package com.nttdata.proyecto01.movementservice._4Infraestructure.data.mogodb;


import com.nttdata.proyecto01.movementservice._3Domain.model.CreditDTO;
import com.nttdata.proyecto01.movementservice._3Domain.model.MovementDTO;
import com.nttdata.proyecto01.movementservice._4Infraestructure.data.document.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;


public interface MovementRepositoryMongoDB extends ReactiveMongoRepository<Movement,String> {

  Flux<MovementDTO> findByIdClientAndNumberCuent(String idClient, String numberCuent);

}
