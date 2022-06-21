package com.nttdata.proyecto01.clientservice.domain.services;

import com.nttdata.proyecto01.clientservice.domain.documents.*;
import com.nttdata.proyecto01.clientservice.domain.interfaces.ClientService;
import com.nttdata.proyecto01.clientservice.infrastructure.data.interfaces.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository _clientRepository;
    private final RestTemplate _restTemplate;

    private final WebClient.Builder _webClientBuilder;
    public ClientServiceImpl(ClientRepository clientRepository, RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
        this._clientRepository = clientRepository;
        this._restTemplate = restTemplate;
        _webClientBuilder = webClientBuilder;
    }
    @Override
    public Flux<ClientDTO> findAllClient() {
        return this._clientRepository.findAllClient();
    }

    @Override
    public Mono<ClientDTO> saveClient(Mono<ClientDTO> clientDTO) {
        return this._clientRepository.saveClient(clientDTO);
    }



    @Override
    public Mono<Void> deleteByIdClient(String id) {
        return this._clientRepository.deleteByIdClient(id);
    }

    @Override
    public Mono<ClientDTO> updateClient(Mono<ClientDTO> clientDTO, String id) {
        return this._clientRepository.updateClient(clientDTO,id);
    }




    @Override
    public Mono<ClientDTO> findByIdClient(String id) {
        return this._clientRepository.findByIdClient(id);
    }

    private Flux<TypeClientDTO> getListType(String idClient){
        Flux<TypeClientDTO> typeClientDTOMono=_webClientBuilder.build()
                .get().uri("http://localhost:9002/typeClient/search/"+idClient)
                .retrieve().bodyToFlux(TypeClientDTO.class);
        return typeClientDTOMono;
    }
    public Flux<BankAccountDTO> getListAccount(String idClient){

        Flux<BankAccountDTO> bankAccountDTOFlux=_webClientBuilder.build()
                .get().uri("http://localhost:9003/account/search/"+idClient)
                .retrieve().bodyToFlux(BankAccountDTO.class);


        return bankAccountDTOFlux;
    }
/*
    public Flux<CreditDTO> getListProducts(String idClient,String idProduct){

        Flux<CreditDTO> creditDTOFlux=_webClientBuilder.build()
                .get().uri("http://localhost:9004/credit/products/"+idClient+"/"+idProduct)
                .retrieve().bodyToFlux(CreditDTO.class);
        return creditDTOFlux;
    }

 */

    @Override
    public Map<String,Object> getListaProducts(String id) {
        Map<String,Object> result=new HashMap<>();

        ProductDTO li=new ProductDTO();
        Mono<ClientDTO> clientDTO=findByIdClient(id);

        result.put("Client",clientDTO);
        Flux<TypeClientDTO> typeClientDTO=getListType(id);
        result.put("Type",typeClientDTO);
        Mono<List<BankAccountDTO>> bankAccountDTO=getListAccount(id).collectList();
        //List<BankAccountDTO> list=bankAccountDTO.block();
        result.put("Account",bankAccountDTO);
        //TypeClientDTO typeClientDTO=getListType(id).block();
        //BankAccountDTO bankAccountDTO=getListAccount(id).block();


        return result;

    }
    /*@Override
    public Map<String,Object> getClientAndProducts(String idclient) {
        WebClient.Builder builder=WebClient.builder();
        Map<String,Object> result=new HashMap<>();
        Mono<ClientDTO> ccc=_clientRepository.findByIdClient(idclient);

        result.put("Client",ccc);

        List<TypeClientDTO> typeClientDTOS=this._restTemplate.getForObject("http://typeclient-service/typeClient/search/"+idclient,List.class);
        if(typeClientDTOS == null){
            result.put("TypeClient","No se encontro TypeClient");
        }else{
            result.put("TypeClient",typeClientDTOS);
        }
        List<BankAccountDTO> bankAccountDTOS=this._restTemplate.getForObject("http://bankaccount-service/account/search/"+idclient,List.class);
        if(typeClientDTOS == null){
            result.put("BankAccount","No se encontro BankAccount");
        }else{
            result.put("BankAccount",bankAccountDTOS);
        }
        List<CreditDTO> creditDTOS=this._restTemplate.getForObject("http://credit-service/credit/search/"+idclient,List.class);
        if(typeClientDTOS == null){
            result.put("Credit","No se encontro Credit");
        }else{
            result.put("Credit",creditDTOS);
        }
        return result;
    }


     */

}
