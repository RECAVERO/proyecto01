package com.nttdata.proyecto01.creditservice.infraestructure.data.repository;

import com.nttdata.proyecto01.creditservice.domain.contract.CreditRepository;
import com.nttdata.proyecto01.creditservice.domain.model.CreditDTO;
import com.nttdata.proyecto01.creditservice.infraestructure.data.mongodb.CreditRepositoryMongoDB;
import com.nttdata.proyecto01.creditservice.util.convert.Convert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class CreditRepositoryImpl implements CreditRepository {
  private final CreditRepositoryMongoDB creditRepository;
  private static final Logger log = LoggerFactory.getLogger(CreditRepositoryImpl.class);

  public CreditRepositoryImpl(CreditRepositoryMongoDB creditRepository) {
    this.creditRepository = creditRepository;
  }

  @Override
  public Flux<CreditDTO> getListCredit() {
    return this.creditRepository.findAll().map(Convert::entityToDto);
  }

  @Override
  public Mono<CreditDTO> getCreditById(String id) {
    return this.creditRepository.findById(id).map(Convert::entityToDto);
  }

  @Override
  public Mono<CreditDTO> saveCredit(Mono<CreditDTO> creditDto) {
    return creditDto.map(Convert::DtoToEntity)
        .flatMap(creditRepository::insert)
        .map(Convert::entityToDto);
  }

  @Override
  public Mono<CreditDTO> updateCredit(Mono<CreditDTO> creditDto, String id) {
    return creditRepository.findById(id)
        .flatMap(p -> creditDto.map(Convert::DtoToEntity)
            .doOnNext(e ->
                e.setId(id)
            ))
        .flatMap(creditRepository::save)
        .map(Convert::entityToDto);
  }

  private Mono<CreditDTO> updateCreditLocal(Mono<CreditDTO> creditDto, String id) {
    return creditRepository.findById(id)
        .flatMap(p -> creditDto.map(Convert::DtoToEntity)
            .doOnNext(e ->
                e.setId(id)
            ))
        .flatMap(creditRepository::save)
        .map(Convert::entityToDto);
  }

  @Override
  public Mono<Void> deleteCreditById(String id) {
    return creditRepository.deleteById(id);
  }

  @Override
  public Flux<CreditDTO> getListByIdClient(String idClient) {
    return creditRepository.findByIdClient(idClient);
  }

  @Override
  public Flux<CreditDTO> getListCreditByIdClientAndIdProduct(String idClient, String idProduct) {
    return creditRepository.findByIdClientAndIdProduct(idClient, idProduct);
  }

  @Override
  public Mono<CreditDTO> getListCreditByIdClientAndIdTypeAndIdProduct(String idClient,
                                                                      String idType, String idProduct) {

    return creditRepository.findByIdClientAndIdTypeAndIdProduct(idClient, idType, idProduct);

  }

  @Override
  public Mono<CreditDTO> getListCreditAll(String idClient, String idType, String idProduct, String numberCuent) {
    return this.creditRepository.findByIdClientAndIdTypeAndIdProductAndNumberCuent(idClient, idType, idProduct, numberCuent).defaultIfEmpty(new CreditDTO());
  }

}
