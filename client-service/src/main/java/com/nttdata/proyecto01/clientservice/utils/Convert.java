package com.nttdata.proyecto01.clientservice.utils;

import com.nttdata.proyecto01.clientservice.domain.models.ClientDto;
import com.nttdata.proyecto01.clientservice.infrastructure.data.documents.Client;
import org.springframework.beans.BeanUtils;

public class Convert {
    //funcion que convierte un dto a entity
    public static ClientDto entityToDto(Client client){
        ClientDto clientDTO=new ClientDto();
        BeanUtils.copyProperties(client,clientDTO);
        return clientDTO;
    }
    //funcion que convierte un entity a dto
    public static Client DtoToEntity(ClientDto clientDTO){
        Client client=new Client();
        BeanUtils.copyProperties(clientDTO,client);
        return client;
    }
}
