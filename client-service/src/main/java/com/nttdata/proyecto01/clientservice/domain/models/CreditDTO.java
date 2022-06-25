package com.nttdata.proyecto01.clientservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditDTO {
    private String typeCredit;
    private String numberAccountCredit;
    private String idClient;
}
