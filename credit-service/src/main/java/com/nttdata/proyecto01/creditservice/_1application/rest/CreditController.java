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
    public Mono<CreditDTO> updateCredit(@RequestBody Mono<CreditDTO> creditDTOMono,@PathVariable String id){
        return this._creditService.updateCredit(creditDTOMono,id,id);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteCreditById(@PathVariable("id") String id){
        return this._creditService.deleteCreditById(id);
    }
    @GetMapping("/operation/{id}")
    public Flux<CreditDTO> getListByIdClient(@PathVariable("id") String id){
        return this._creditService.getListByIdClient(id);
    }
    @GetMapping("/products/{idclient}/{idproduct}")
    public Flux<CreditDTO> getListByIdClient(@PathVariable("idclient") String idclient,@PathVariable("idproduct") String idproduct){
        return this._creditService.getListCreditByIdClientAndIdProduct(idclient,idproduct);
    }

    @GetMapping("/products/{idclient}/{idtype}/{idproduct}")
    public Mono<CreditDTO> getListByIdClient(@PathVariable String idclient,@PathVariable String idtype,@PathVariable String idproduct){
        return this._creditService.getListCreditByIdClientAndIdTypeAndIdProduct(idclient,idtype,idproduct);
    }

}
