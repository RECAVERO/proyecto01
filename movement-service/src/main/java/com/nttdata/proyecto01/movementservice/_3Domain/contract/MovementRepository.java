package com.nttdata.proyecto01.movementservice._3Domain.contract;

import com.nttdata.proyecto01.movementservice._3Domain.model.CreditDTO;
import com.nttdata.proyecto01.movementservice._3Domain.model.MovementDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovementRepository {
    Flux<MovementDTO> getListMovement();
    Mono<MovementDTO> getMovementById(String id);
    Mono<MovementDTO> saveMovement(Mono<MovementDTO> movementDTOMono);
    Mono<MovementDTO> updateMovement(Mono<MovementDTO> movementDTOMono, String id);
    Mono<Void> deleteMovementById(String id);
}
