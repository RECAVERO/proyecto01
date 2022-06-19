package com.nttdata.proyecto01.bankaccountservice._4infraestructure.data.mongodb;

import com.nttdata.proyecto01.bankaccountservice._4infraestructure.data.document.BankAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BankAccountRepositoryMongoDb extends ReactiveMongoRepository<BankAccount,String> {
}
