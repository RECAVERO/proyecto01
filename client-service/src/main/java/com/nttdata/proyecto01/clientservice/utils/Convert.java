package com.nttdata.proyecto01.clientservice.utils;

import com.nttdata.proyecto01.clientservice.domain.models.ClientDto;
import com.nttdata.proyecto01.clientservice.infrastructure.data.documents.Client;
import org.springframework.beans.BeanUtils;

/**
 * clase que convierte un document a un documento DTO.
 */

public class Convert {

  /**
   * Metodo que transforma de documento a documento DTO.
   *
   * @param client documento que tiene los campos de la tabla en mongoDB.
   * @return clientDto para su translado por toda la aplicacion.
   */
  public static ClientDto entityToDto(Client client) {
    ClientDto clientDto = new ClientDto();
    BeanUtils.copyProperties(client, clientDto);
    return clientDto;
  }

  /**
   * Metodo que transforma de documento DTO a documento.
   *
   * @param clientDto documento DTO que reccorio toda la aplicacion.
   * @return client se obtiene un documento que interactua con la base de datos.
   */
  public static Client dtoToEntity(ClientDto clientDto) {
    Client client = new Client();
    BeanUtils.copyProperties(clientDto, client);
    return client;
  }
}
