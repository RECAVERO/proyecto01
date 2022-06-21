package com.nttdata.proyecto01.clientservice.application.rest;

import com.nttdata.proyecto01.clientservice.domain.documents.BankAccountDTO;
import com.nttdata.proyecto01.clientservice.domain.documents.ClientDTO;
import com.nttdata.proyecto01.clientservice.domain.documents.ProductDTO;
import com.nttdata.proyecto01.clientservice.domain.documents.TypeClientDTO;
import com.nttdata.proyecto01.clientservice.domain.interfaces.ClientService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService _clientService;

    public ClientController(ClientService clientService) {
        this._clientService = clientService;
    }

    @GetMapping
    public Flux<ClientDTO> getListClient(){
        return this._clientService.findAllClient();
    }
    @PostMapping
    public Mono<ClientDTO> addClient(@RequestBody Mono<ClientDTO> clientDTO){
        return this._clientService.saveClient(clientDTO);
    }
    @GetMapping("/{id}")
    public Mono<ClientDTO> getListClient(@PathVariable String id){
        return this._clientService.findByIdClient(id);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteClient(@PathVariable String id){
        return this._clientService.deleteByIdClient(id);
    }
    @PutMapping("/{id}")
    public Mono<ClientDTO> updateClient(@RequestBody Mono<ClientDTO> clientDTO,@PathVariable String id){
        return this._clientService.updateClient(clientDTO,id);
    }
    @GetMapping("/products/{id}")
    public Map<String,Object> getListClientAllProducts(@PathVariable String id){
        return this._clientService.getListaProducts(id);
    }




}
