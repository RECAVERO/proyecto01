package com.nttdata.proyecto01.bankaccountservice._4infraestructure.data.mongodb;

import com.nttdata.proyecto01.bankaccountservice._3domain.model.BankAccountDTO;
import com.nttdata.proyecto01.bankaccountservice._4infraestructure.data.document.BankAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface BankAccountRepositoryMongoDb extends ReactiveMongoRepository<BankAccount,String> {
    //Flux<BankAccountDTO> findByIdClient(String idAccount);
}
