package com.nttdata.proyecto01.movementservice._2Task.service;

import com.nttdata.proyecto01.movementservice._2Task.interfaces.MovementService;
import com.nttdata.proyecto01.movementservice._3Domain.contract.MovementRepository;
import com.nttdata.proyecto01.movementservice._3Domain.model.CreditDTO;
import com.nttdata.proyecto01.movementservice._3Domain.model.MovementDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class MovementServiceImpl implements MovementService {
    private final MovementRepository _movementRepository;
    private final WebClient.Builder _webClientBuilder;
    public MovementServiceImpl(MovementRepository movementRepository, WebClient.Builder webClientBuilder) {
        _movementRepository=movementRepository;
        _webClientBuilder = webClientBuilder;
    }

    @Override
    public Flux<MovementDTO> getListMovement() {
        return _movementRepository.getListMovement();
    }

    @Override
    public Mono<MovementDTO> getMovementById(String id) {
        return _movementRepository.getMovementById(id);
    }

    @Override
    public Mono<MovementDTO> saveMovement(Mono<MovementDTO> movementDTOMono) {

        return _movementRepository.saveMovement(movementDTOMono);
    }

    @Override
    public Mono<MovementDTO> updateMovement(Mono<MovementDTO> movementDTOMono, String id) {
        return _movementRepository.updateMovement(movementDTOMono,id);
    }

    @Override
    public Mono<Void> deleteMovementById(String id) {
        return _movementRepository.deleteMovementById(id);
    }

    @Override
    public Flux<CreditDTO> getListCredit(String idClient, String idProduct) {
        Flux<CreditDTO> creditDTOFlux=_webClientBuilder.build()
                .get().uri("http://localhost:9004/credit/products/"+idClient+"/"+idProduct)
                .retrieve().bodyToFlux(CreditDTO.class);
        return creditDTOFlux;
    }
}
