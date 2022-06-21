package com.nttdata.proyecto01.clientservice.domain.interfaces;

import com.nttdata.proyecto01.clientservice.domain.documents.BankAccountDTO;
import com.nttdata.proyecto01.clientservice.domain.documents.ClientDTO;
import com.nttdata.proyecto01.clientservice.domain.documents.CreditDTO;
import com.nttdata.proyecto01.clientservice.domain.documents.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface ClientService {
    Flux<ClientDTO> findAllClient();
    Mono<ClientDTO> saveClient(Mono<ClientDTO> clientDTO);
    Mono<ClientDTO> findByIdClient(String id);
    Mono<Void> deleteByIdClient(String id);
    Mono<ClientDTO> updateClient(Mono<ClientDTO> clientDTO,String id);
    Map<String,Object> getListaProducts(String id);
    //Flux<BankAccountDTO> getListAccount(String idClient);
}
