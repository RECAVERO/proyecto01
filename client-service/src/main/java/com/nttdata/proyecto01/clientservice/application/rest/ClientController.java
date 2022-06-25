package com.nttdata.proyecto01.clientservice.application.rest;

import com.nttdata.proyecto01.clientservice.domain.interfaces.ClientService;
import com.nttdata.proyecto01.clientservice.domain.models.ClientDto;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
public class ClientController {
  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping
  public Flux<ClientDto> getListClient() {
    return this.clientService.findAllClient();
  }

  @GetMapping("/{id}")
  public Mono<ClientDto> getListClient(@PathVariable String id) {
    return this.clientService.findByIdClient(id);
  }

  @GetMapping("/products/{id}")
  public Map<String, Object> getListClientAllProducts(@PathVariable String id) {
    return this.clientService.getListaProducts(id);
  }

  @PostMapping
  public Mono<ClientDto> addClient(@RequestBody Mono<ClientDto> clientDto) {
    return this.clientService.saveClient(clientDto);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> deleteClient(@PathVariable String id) {
    return this.clientService.deleteByIdClient(id);
  }

  @PutMapping("/{id}")
  public Mono<ClientDto> updateClient(@RequestBody Mono<ClientDto> cli, @PathVariable String id) {
    return this.clientService.updateClient(cli, id);
  }
}
