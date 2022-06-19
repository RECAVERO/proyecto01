package com.nttdata.proyecto01.creditservice._1application.rest;

import com.nttdata.proyecto01.creditservice._2task.interfaces.CreditService;
import com.nttdata.proyecto01.creditservice._3domain.model.CreditDTO;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/credit")
public class CreditController {
    private final CreditService _creditService;

    public CreditController(CreditService creditService) {
        _creditService = creditService;
    }
    @GetMapping
    public Flux<CreditDTO> getListCredit(){
        return this._creditService.getListCredit();
    }

    @GetMapping("/{id}")
    public Mono<CreditDTO> getCreditById(@PathVariable String id){
        return this._creditService.getCreditById(id);
    }

    @PostMapping
    public Mono<CreditDTO> saveCredit(@RequestBody Mono<CreditDTO> creditDTOMono){
        return this._creditService.saveCredit(creditDTOMono);
    }
    @PutMapping("/{id}")
    public Mono<CreditDTO> updateCredit(@RequestBody Mono<CreditDTO> creditDTOMono,@PathVariable("id") String id){
        return this._creditService.updateCredit(creditDTOMono,id);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteCreditById(@PathVariable("id") String id){
        return this._creditService.deleteCreditById(id);
    }
    @GetMapping("/search/{id}")
    public Flux<CreditDTO> getCreditByIdClient(@PathVariable("id") String id){
        return this._creditService.getCreditByIdClient(id);
    }
}
