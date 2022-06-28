package com.nttdata.proyecto01.clientservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase Auxiliar que obtiene los datos para mostrarlos al cliente.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
  private String status;
  private String msg;
  private ClientDto client;
}
