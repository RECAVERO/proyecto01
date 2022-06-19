package com.nttdata.proyecto01.clientservice.utils;

import com.nttdata.proyecto01.clientservice.domain.documents.ClientDTO;
import com.nttdata.proyecto01.clientservice.infrastructure.data.documents.Client;
import org.springframework.beans.BeanUtils;

public class Convert {
    //funcion que convierte un dto a entity
    public static ClientDTO entityToDto(Client client){
        ClientDTO clientDTO=new ClientDTO();
        BeanUtils.copyProperties(client,clientDTO);
        return clientDTO;
    }
    //funcion que convierte un entity a dto
    public static Client DtoToEntity(ClientDTO clientDTO){
        Client client=new Client();
        BeanUtils.copyProperties(clientDTO,client);
        return client;
    }
}
