package com.nttdata.proyecto01.clientservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private ClientDto clientDTO;
    private TypeClientDTO typeClientDTOList;
    private BankAccountDTO bankAccountDTOList;
}
