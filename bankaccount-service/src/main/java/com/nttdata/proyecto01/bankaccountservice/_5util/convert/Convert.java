package com.nttdata.proyecto01.bankaccountservice._5util.convert;

import com.nttdata.proyecto01.bankaccountservice._3domain.model.BankAccountDTO;
import com.nttdata.proyecto01.bankaccountservice._4infraestructure.data.document.BankAccount;
import org.springframework.beans.BeanUtils;

public class Convert {
    public static BankAccountDTO entityToDto(BankAccount bankAccount){
        BankAccountDTO bankAccountDTO=new BankAccountDTO();
        BeanUtils.copyProperties(bankAccount,bankAccountDTO);
        return bankAccountDTO;
    }
    public static BankAccount DtoToEntity(BankAccountDTO bankAccountDTO){
        BankAccount bankAccount=new BankAccount();
        BeanUtils.copyProperties(bankAccountDTO,bankAccount);
        return bankAccount;
    }
}
