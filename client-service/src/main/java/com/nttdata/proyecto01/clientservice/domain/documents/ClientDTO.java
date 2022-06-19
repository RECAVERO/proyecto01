package com.nttdata.proyecto01.clientservice.domain.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String id;
    private String names;
    private String FatherSurname;
    private String MatherSurname;
}
