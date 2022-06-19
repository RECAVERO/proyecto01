package com.nttdata.proyecto01.clientservice.domain.services;

import com.nttdata.proyecto01.clientservice.application.rest.ClientController;
import com.nttdata.proyecto01.clientservice.domain.documents.BankAccountDTO;
import com.nttdata.proyecto01.clientservice.domain.documents.ClientDTO;
import com.nttdata.proyecto01.clientservice.domain.documents.CreditDTO;
import com.nttdata.proyecto01.clientservice.domain.documents.TypeClientDTO;
import com.nttdata.proyecto01.clientservice.domain.interfaces.ClientService;
import com.nttdata.proyecto01.clientservice.infrastructure.data.documents.Client;
import com.nttdata.proyecto01.clientservice.infrastructure.data.interfaces.ClientRepository;
import com.nttdata.proyecto01.clientservice.utils.Convert;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository _clientRepository;
    private final RestTemplate _restTemplate;
    public ClientServiceImpl(ClientRepository clientRepository, RestTemplate restTemplate) {
        this._clientRepository = clientRepository;
        this._restTemplate = restTemplate;
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
    @Override
    public Map<String,Object> getClientAndProducts(String idclient) {
        Map<String,Object> result=new HashMap<>();
        Mono<ClientDTO> ccc=_clientRepository.findByIdClient(idclient);

        result.put("Client",ccc);

        List<TypeClientDTO> typeClientDTOS=this._restTemplate.getForObject("http://localhost:9002/typeClient/search/"+idclient,List.class);
        if(typeClientDTOS == null){
            result.put("TypeClient","No se encontro TypeClient");
        }else{
            result.put("TypeClient",typeClientDTOS);
        }
        List<BankAccountDTO> bankAccountDTOS=this._restTemplate.getForObject("http://localhost:9003/account/search/"+idclient,List.class);
        if(typeClientDTOS == null){
            result.put("BankAccount","No se encontro BankAccount");
        }else{
            result.put("BankAccount",bankAccountDTOS);
        }
        List<CreditDTO> creditDTOS=this._restTemplate.getForObject("http://localhost:9004/credit/search/"+idclient,List.class);
        if(typeClientDTOS == null){
            result.put("Credit","No se encontro Credit");
        }else{
            result.put("Credit",creditDTOS);
        }
        return result;
    }
}
