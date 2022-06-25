package com.nttdata.proyecto01.bankaccountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BankaccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankaccountServiceApplication.class, args);
	}

}
