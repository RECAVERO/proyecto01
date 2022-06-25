package com.nttdata.proyecto01.bankaccountservice._4infraestructure.data.repository;

import com.nttdata.proyecto01.bankaccountservice._3domain.contract.BankAccountRepository;
import com.nttdata.proyecto01.bankaccountservice._3domain.model.BankAccountDTO;
import com.nttdata.proyecto01.bankaccountservice._4infraestructure.data.mongodb.BankAccountRepositoryMongoDb;
import com.nttdata.proyecto01.bankaccountservice._5util.convert.Convert;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

    private final BankAccountRepositoryMongoDb _bankAccountRepositoryMongoDb;

    public BankAccountRepositoryImpl(BankAccountRepositoryMongoDb bankAccountRepositoryMongoDb) {
        _bankAccountRepositoryMongoDb = bankAccountRepositoryMongoDb;
    }


    @Override
    public Flux<BankAccountDTO> getListBankAccount() {
        return _bankAccountRepositoryMongoDb.findAll().map(Convert::entityToDto);
    }

    @Override
    public Mono<BankAccountDTO> getBankAccountById(String id) {
        return _bankAccountRepositoryMongoDb.findById(id).map(Convert::entityToDto);
    }

    @Override
    public Mono<BankAccountDTO> saveBankAccount(Mono<BankAccountDTO> bankAccountDTOMono) {
        return bankAccountDTOMono.map(Convert::DtoToEntity)
                .flatMap(_bankAccountRepositoryMongoDb::insert)
                .map(Convert::entityToDto);
    }

    @Override
    public Mono<BankAccountDTO> updateBankAccount(Mono<BankAccountDTO> bankAccountDTOMono, String id) {
        return _bankAccountRepositoryMongoDb.findById(id)
                .flatMap(p -> bankAccountDTOMono.map(Convert::DtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(_bankAccountRepositoryMongoDb::save)
                .map(Convert::entityToDto);
    }

    @Override
    public Mono<Void> deleteBankAccountById(String id) {
        return _bankAccountRepositoryMongoDb.deleteById(id);
    }

    /*@Override
    public Flux<BankAccountDTO> getBankAccountByIdClient(String idAccount) {
        return this._bankAccountRepositoryMongoDb.findByIdClient(idAccount);
    }*/
}
