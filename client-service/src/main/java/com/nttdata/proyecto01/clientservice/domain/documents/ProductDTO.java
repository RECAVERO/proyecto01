package com.nttdata.proyecto01.clientservice.domain.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private ClientDTO clientDTO;
    private TypeClientDTO typeClientDTOList;
    private BankAccountDTO bankAccountDTOList;
}
