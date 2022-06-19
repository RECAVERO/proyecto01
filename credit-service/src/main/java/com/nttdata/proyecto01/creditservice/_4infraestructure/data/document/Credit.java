package com.nttdata.proyecto01.creditservice._4infraestructure.data.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("Credits")
public class Credit {
    @Id
    private String id;
    private String typeCredit;
    private String numberAccountCredit;
    private String idClient;
}
