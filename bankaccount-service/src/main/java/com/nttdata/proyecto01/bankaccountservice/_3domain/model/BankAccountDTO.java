package com.nttdata.proyecto01.bankaccountservice._3domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {
    private String id;
    private String idAccount;
    private String typeAccount;
}
