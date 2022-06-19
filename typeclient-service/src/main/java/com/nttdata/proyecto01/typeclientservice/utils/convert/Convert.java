package com.nttdata.proyecto01.typeclientservice.utils.convert;

import com.nttdata.proyecto01.typeclientservice._3domain.model.TypeClientDTO;
import com.nttdata.proyecto01.typeclientservice.infraestructure.data.document.TypeClient;
import org.springframework.beans.BeanUtils;

public class Convert {

    public static TypeClientDTO entityToDto(TypeClient typeClient){
        TypeClientDTO _typeclientDTO=new TypeClientDTO();
        BeanUtils.copyProperties(typeClient,_typeclientDTO);
        return _typeclientDTO;
    }
    public static TypeClient DtoToEntity(TypeClientDTO clientDTO){
        TypeClient _typeclient=new TypeClient();
        BeanUtils.copyProperties(clientDTO,_typeclient);
        return _typeclient;
    }
}
