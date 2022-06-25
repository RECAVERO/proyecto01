package com.nttdata.proyecto01.creditservice._3domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditDTO {
    private String id;
    private String idClient;
    private String idType;
    private String idProduct;
    private float balance;
}
