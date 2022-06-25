package com.nttdata.proyecto01.clientservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDTO {
    private String typeAccount;
    private String numberAccount;
    private String idClient;
    private String idTypeClient;
}
