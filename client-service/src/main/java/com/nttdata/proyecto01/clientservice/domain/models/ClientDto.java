package com.nttdata.proyecto01.clientservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase auxiliar de un documento; para la transferencia de datos.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
  private String id;
  private String idClient;
  private String names;
}
