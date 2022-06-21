package com.nttdata.proyecto01.bankaccountservice._2task.service;

import com.nttdata.proyecto01.bankaccountservice._2task.interfaces.BankAccountService;
import com.nttdata.proyecto01.bankaccountservice._3domain.contract.BankAccountRepository;
import com.nttdata.proyecto01.bankaccountservice._3domain.model.BankAccountDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository _bankAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        _bankAccountRepository = bankAccountRepository;
    }

    @Override
    public Flux<BankAccountDTO> getListBankAccount() {
        return _bankAccountRepository.getListBankAccount();
    }

    @Override
    public Mono<BankAccountDTO> getBankAccountById(String id) {
        return _bankAccountRepository.getBankAccountById(id);
    }

    @Override
    public Mono<BankAccountDTO> saveBankAccount(Mono<BankAccountDTO> bankAccountDTOMono) {
        return _bankAccountRepository.saveBankAccount(bankAccountDTOMono);
    }

    @Override
    public Mono<BankAccountDTO> updateBankAccount(Mono<BankAccountDTO> bankAccountDTOMono, String id) {
        return _bankAccountRepository.updateBankAccount(bankAccountDTOMono,id);
    }

    @Override
    public Mono<Void> deleteBankAccountById(String id) {
        return _bankAccountRepository.deleteBankAccountById(id);
    }

   /* @Override
    public Flux<BankAccountDTO> getBankAccountByIdClient(String idClient) {
        return this._bankAccountRepository.getBankAccountByIdClient(idClient);
    }

    */
}
