package com.nttdata.proyecto01.creditservice._2task.service;

import com.nttdata.proyecto01.creditservice._2task.interfaces.CreditService;
import com.nttdata.proyecto01.creditservice._3domain.contract.CreditRepository;
import com.nttdata.proyecto01.creditservice._3domain.model.CreditDTO;
import com.nttdata.proyecto01.creditservice._5util.convert.Convert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class CreditServiceImpl implements CreditService {
    private final CreditRepository _creditRepository;


    public CreditServiceImpl(CreditRepository creditRepository) {
        _creditRepository = creditRepository;
    }

    @Override
    public Flux<CreditDTO> getListCredit() {
        return _creditRepository.getListCredit();
    }

    @Override
    public Mono<CreditDTO> getCreditById(String id) {
        return _creditRepository.getCreditById(id);
    }

    @Override
    public Mono<CreditDTO> saveCredit(Mono<CreditDTO> creditDTOMono) {
        return _creditRepository.saveCredit(creditDTOMono);
    }

    @Override
    public Mono<CreditDTO> updateCredit(Mono<CreditDTO> creditDTOMono, String idclient,String idproduct) {
        return _creditRepository.updateCredit(creditDTOMono,idclient,idproduct);
    }


    @Override
    public Mono<Void> deleteCreditById(String id) {
        return _creditRepository.deleteCreditById(id);
    }

    @Override
    public Flux<CreditDTO> getListByIdClient(String idClient) {
        return _creditRepository.getListByIdClient(idClient);
    }

    @Override
    public Flux<CreditDTO> getListCreditByIdClientAndIdProduct(String idClient, String idProduct) {
        return _creditRepository.getListCreditByIdClientAndIdProduct(idClient,idProduct);
    }

    @Override
    public Mono<CreditDTO> getListCreditByIdClientAndIdTypeAndIdProduct( String idClient, String idType, String idProduct) {
        return _creditRepository.getListCreditByIdClientAndIdTypeAndIdProduct(idClient,idType,idProduct);
    }



}
