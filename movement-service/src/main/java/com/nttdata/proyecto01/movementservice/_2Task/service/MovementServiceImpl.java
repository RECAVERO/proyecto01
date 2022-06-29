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
    public Mono<CreditDTO> getListCredit(String idClient,String idType, String idProduct, String numberCuent) {
        Mono<CreditDTO> creditDTOMono=_webClientBuilder.build()
                .get().uri("http://localhost:9004/credit/products/"+idClient+"/"+idType + "/"+idProduct + "/" + numberCuent)
                .retrieve().bodyToMono(CreditDTO.class);
        creditDTOMono.subscribe(c->{
            System.out.println("Codigo"+c.getId() +"Client" + c.getIdClient()+"type"+c.getIdType() +"product" + c.getIdProduct());
        });


        return creditDTOMono;
    }

    @Override
    public Mono<CreditDTO> updateCreditDeposit(Mono<CreditDTO> creditDto) {
        return creditDto.flatMap(credi->{
                return _webClientBuilder.build()
                    .post().uri("http://localhost:9004/credit/deposit")
                    .body(creditDto, CreditDTO.class)
                    .retrieve()
                    .bodyToMono(CreditDTO.class);
        });
    }
    @Override
    public Mono<CreditDTO> updateCreditWithdrawal(Mono<CreditDTO> creditDto) {
        return creditDto.flatMap(credi->{
            return _webClientBuilder.build()
                .post().uri("http://localhost:9004/credit/withdrawal")
                .body(creditDto, CreditDTO.class)
                .retrieve()
                .bodyToMono(CreditDTO.class);
        });
    }

    @Override
    public Flux<MovementDTO> getListRecordMovement(String idClient, String numberCuent) {
        return _movementRepository.getListRecordMovement(idClient,numberCuent);
    }
}
