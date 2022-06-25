package com.nttdata.proyecto01.creditservice._5util.convert;

import com.nttdata.proyecto01.creditservice._3domain.model.CreditDTO;
import com.nttdata.proyecto01.creditservice._4infraestructure.data.document.Credit;
import org.springframework.beans.BeanUtils;

public class Convert {
    public static CreditDTO entityToDto(Credit credit){
        CreditDTO creditDTO=new CreditDTO();
        BeanUtils.copyProperties(credit,creditDTO);
        return creditDTO;
    }
    public static Credit DtoToEntity(CreditDTO creditDTO){
        Credit credit=new Credit();
        BeanUtils.copyProperties(creditDTO,credit);
        return credit;
    }
}
