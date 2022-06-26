package com.nttdata.proyecto01.creditservice.btask.service;

import com.nttdata.proyecto01.creditservice.btask.interfaces.CreditService;
import com.nttdata.proyecto01.creditservice.domain.contract.CreditRepository;
import com.nttdata.proyecto01.creditservice.domain.model.CreditDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {

  private final CreditRepository creditRepository;


  public CreditServiceImpl(CreditRepository creditRepository) {
    this.creditRepository = creditRepository;
  }

  @Override
  public Flux<CreditDTO> getListCredit() {
    return creditRepository.getListCredit();
  }

  @Override
  public Mono<CreditDTO> getCreditById(String id) {
    return creditRepository.getCreditById(id);
  }

  @Override
  public Mono<CreditDTO> saveCredit(Mono<CreditDTO> creditDto) {
    return creditRepository.saveCredit(creditDto);
  }

  @Override
  public Mono<CreditDTO> updateCredit(Mono<CreditDTO> creditDto,
                                      String idclient,
                                      String idproduct) {
    return creditRepository.updateCredit(creditDto, idclient, idproduct);
  }


  @Override
  public Mono<Void> deleteCreditById(String id) {
    return creditRepository.deleteCreditById(id);
  }

  @Override
  public Flux<CreditDTO> getListByIdClient(String idClient) {
    return creditRepository.getListByIdClient(idClient);
  }

  @Override
  public Flux<CreditDTO> getListCreditByIdClientAndIdProduct(String idClient, String idProduct) {
    return creditRepository.getListCreditByIdClientAndIdProduct(idClient, idProduct);
  }

  @Override
  public Mono<CreditDTO> getListCreditByIdClientAndIdTypeAndIdProduct(String idClient,
                                                                       String idType,
                                                                       String idProduct) {
    return creditRepository.getListCreditByIdClientAndIdTypeAndIdProduct(idClient, idType, idProduct);
  }



}
