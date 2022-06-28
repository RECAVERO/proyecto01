package com.nttdata.proyecto01.movementservice._4Infraestructure.data.repository;

import com.nttdata.proyecto01.movementservice._3Domain.contract.MovementRepository;
import com.nttdata.proyecto01.movementservice._3Domain.model.CreditDTO;
import com.nttdata.proyecto01.movementservice._3Domain.model.MovementDTO;
import com.nttdata.proyecto01.movementservice._4Infraestructure.data.mogodb.MovementRepositoryMongoDB;
import com.nttdata.proyecto01.movementservice._5Utils.convert.Convert;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public class MovementRepositoryImpl implements MovementRepository {
    private final MovementRepositoryMongoDB _movementRepositoryMongoDB;

    public MovementRepositoryImpl(MovementRepositoryMongoDB movementRepositoryMongoDB) {
        _movementRepositoryMongoDB = movementRepositoryMongoDB;
    }

    @Override
    public Flux<MovementDTO> getListMovement() {
        return _movementRepositoryMongoDB.findAll().map(Convert::entityToDto);
    }

    @Override
    public Mono<MovementDTO> getMovementById(String id) {
        return _movementRepositoryMongoDB.findById(id).map(Convert::entityToDto);
    }

    @Override
    public Mono<MovementDTO> saveMovement(Mono<MovementDTO> movementDTOMono) {
        return movementDTOMono.map(Convert::DtoToEntity)
                .flatMap(_movementRepositoryMongoDB::insert)
                .map(Convert::entityToDto);
    }

    @Override
    public Mono<MovementDTO> updateMovement(Mono<MovementDTO> movementDTOMono, String id) {
        return _movementRepositoryMongoDB.findById(id)
                .flatMap(p -> movementDTOMono.map(Convert::DtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(_movementRepositoryMongoDB::save)
                .map(Convert::entityToDto);
    }

    @Override
    public Mono<Void> deleteMovementById(String id) {
        return _movementRepositoryMongoDB.deleteById(id);
    }

    @Override
    public Flux<MovementDTO> getListRecordMovement(String idClient, String numberCuent) {
        return _movementRepositoryMongoDB.findByIdClientAndNumberCuent(idClient,numberCuent);
    }

}
