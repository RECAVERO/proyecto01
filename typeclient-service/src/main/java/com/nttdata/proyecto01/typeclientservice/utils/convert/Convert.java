package com.nttdata.proyecto01.typeclientservice.utils.convert;

import com.nttdata.proyecto01.typeclientservice.domain.model.TypeDto;
import com.nttdata.proyecto01.typeclientservice.infraestructure.data.document.TypeClient;
import org.springframework.beans.BeanUtils;

public class Convert {

    public static TypeDto entityToDto(TypeClient typeClient){
        TypeDto _typeclientDTO=new TypeDto();
        BeanUtils.copyProperties(typeClient,_typeclientDTO);
        return _typeclientDTO;
    }
    public static TypeClient DtoToEntity(TypeDto clientDTO){
        TypeClient _typeclient=new TypeClient();
        BeanUtils.copyProperties(clientDTO,_typeclient);
        return _typeclient;
    }
}
