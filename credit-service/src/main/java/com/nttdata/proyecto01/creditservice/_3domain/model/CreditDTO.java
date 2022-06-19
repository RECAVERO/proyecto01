package com.nttdata.proyecto01.creditservice._3domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credit {
    private String id;
    private String typeCredit;
    private String numberAccountCredit;
    private String idClient;
}
