package com.nttdata.infraestructure.mongodb;

import com.nttdata.domain.models.MovementDto;
import com.nttdata.infraestructure.document.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MovementRepositoryMongodb extends ReactiveMongoRepository<Movement, String> {
    Flux<MovementDto> findByIdClientAndNumberCuent(String idClient, String numberCuent);
}
