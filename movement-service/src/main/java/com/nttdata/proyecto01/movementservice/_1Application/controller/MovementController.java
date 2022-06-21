package com.nttdata.proyecto01.movementservice._1Application.controller;

import com.nttdata.proyecto01.movementservice._2Task.interfaces.MovementService;
import com.nttdata.proyecto01.movementservice._3Domain.model.CreditDTO;
import com.nttdata.proyecto01.movementservice._3Domain.model.MovementDTO;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Movement")
public class MovementController {
    private final MovementService _movementService;

    public MovementController(MovementService movementService) {
        _movementService = movementService;
    }
    @GetMapping
    public Flux<MovementDTO> getListMovement(){
        return _movementService.getListMovement();
    }
    @GetMapping("/{id}")
    public Mono<MovementDTO> getMovementById(@PathVariable String id){
        return _movementService.getMovementById(id);
    }
    @PostMapping
    public Mono<MovementDTO> saveMovement(@RequestBody Mono<MovementDTO> movementDTOMono){
        return _movementService.saveMovement(movementDTOMono);
    }
    @PutMapping("/{id}")
    public Mono<MovementDTO> updateMovement(@RequestBody Mono<MovementDTO> movementDTOMono,@PathVariable String id){
        return _movementService.saveMovement(movementDTOMono);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteMovementById(@PathVariable String id){
        return _movementService.deleteMovementById(id);
    }
    @GetMapping("/operation/{idclient}/{idproduct}")
    public Flux<CreditDTO> getListProducts(@PathVariable String idclient,@PathVariable String idproduct){
        return _movementService.getListCredit(idclient,idproduct);
    }


}
