package com.nttdata.proyecto01.clientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Clase Principal.
 */

@SpringBootApplication
@EnableEurekaClient
public class ClientServiceApplication {

  /**
   * Metodo de inicio de la aplicacion.
   *
   * @param args todos los argumentos iniciales.
   */

  public static void main(String[] args) {

    SpringApplication.run(ClientServiceApplication.class, args);

  }

}
