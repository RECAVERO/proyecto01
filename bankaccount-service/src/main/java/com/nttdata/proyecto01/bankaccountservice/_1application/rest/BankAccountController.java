package com.nttdata.proyecto01.bankaccountservice._1application.rest;

import com.nttdata.proyecto01.bankaccountservice._2task.interfaces.BankAccountService;
import com.nttdata.proyecto01.bankaccountservice._3domain.model.BankAccountDTO;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
public class BankAccountController {
    private final BankAccountService _bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        _bankAccountService = bankAccountService;
    }

    @GetMapping
    public Flux<BankAccountDTO> getListBankAccount(){
        return this._bankAccountService.getListBankAccount();
    }

    @GetMapping("/{id}")
    public Mono<BankAccountDTO> getListBankAccount(@PathVariable String id){
        return this._bankAccountService.getBankAccountById(id);
    }

    @PostMapping
    public Mono<BankAccountDTO> saveBankAccount(@RequestBody Mono<BankAccountDTO> bankAccountDTOMono){
        return this._bankAccountService.saveBankAccount(bankAccountDTOMono);
    }
    @PutMapping("/{id}")
    public Mono<BankAccountDTO> updateBankAccount(@RequestBody Mono<BankAccountDTO> bankAccountDTOMono,@PathVariable("id") String id){
        return this._bankAccountService.updateBankAccount(bankAccountDTOMono,id);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteBankAccount(@PathVariable("id") String id){
        return this._bankAccountService.deleteBankAccountById(id);
    }
    /*@GetMapping("/search/{id}")
    public Flux<BankAccountDTO> getListBankAccountByIdClient(@PathVariable("id") String id){
        return this._bankAccountService.getBankAccountByIdClient(id);
    }

     */
}
