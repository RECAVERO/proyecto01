package com.nttdata.proyecto01.bankaccountservice._2task.interfaces;

import com.nttdata.proyecto01.bankaccountservice._3domain.model.BankAccountDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankAccountService {
    Flux<BankAccountDTO> getListBankAccount();
    Mono<BankAccountDTO> getBankAccountById(String id);
    Mono<BankAccountDTO> saveBankAccount(Mono<BankAccountDTO> bankAccountDTOMono);
    Mono<BankAccountDTO> updateBankAccount(Mono<BankAccountDTO> bankAccountDTOMono, String id);
    Mono<Void> deleteBankAccountById(String id);
    //Flux<BankAccountDTO> getBankAccountByIdClient(String idClient);
}
