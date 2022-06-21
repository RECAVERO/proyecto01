package com.nttdata.proyecto01.typeclientservice._1application.rest;

import com.nttdata.proyecto01.typeclientservice._2task.interfaces.TypeClientService;
import com.nttdata.proyecto01.typeclientservice._3domain.model.TypeClientDTO;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/typeClient")
public class TypeClienteController {
    private final TypeClientService _typeClientService;

    public TypeClienteController(TypeClientService typeClientService) {
        this._typeClientService = typeClientService;
    }
    @GetMapping
    public Flux<TypeClientDTO> getListTypeClient(){
        return this._typeClientService.getListTypeClient();
    }

    @GetMapping("/{id}")
    public Mono<TypeClientDTO> getListTypeClient(@PathVariable("id") String id){
        return this._typeClientService.getTypeClientById(id);
    }
    @PostMapping
    public Mono<TypeClientDTO> saveClient(@RequestBody Mono<TypeClientDTO> typeClientDTO){
        return this._typeClientService.saveTypeClient(typeClientDTO);
    }
    @PutMapping("/{id}")
    public Mono<TypeClientDTO> updateTypeClient(@RequestBody Mono<TypeClientDTO> typeClientDTO,@PathVariable("id") String id){
        return this._typeClientService.updateTypeClient(typeClientDTO,id);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteTypeClient(@PathVariable("id") String id){
        return this._typeClientService.deleteTypeClientById(id);
    }
   /* @GetMapping("/search/{id}")
    public Flux<TypeClientDTO> getListTypeClientByIdClient(@PathVariable("id") String id){
        return this._typeClientService.getTypeClientByIdClient(id);
    }

    */

}
