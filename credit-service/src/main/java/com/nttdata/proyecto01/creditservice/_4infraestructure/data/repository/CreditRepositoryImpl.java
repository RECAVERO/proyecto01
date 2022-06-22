package com.nttdata.proyecto01.creditservice._4infraestructure.data.repository;

import com.nttdata.proyecto01.creditservice._2task.service.CreditServiceImpl;
import com.nttdata.proyecto01.creditservice._3domain.contract.CreditRepository;
import com.nttdata.proyecto01.creditservice._3domain.model.CreditDTO;
import com.nttdata.proyecto01.creditservice._4infraestructure.data.mongodb.CreditRepositoryMongoDB;
import com.nttdata.proyecto01.creditservice._5util.convert.Convert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public class CreditRepositoryImpl implements CreditRepository {
    private final CreditRepositoryMongoDB creditRepositoryMongoDB;
    private static final Logger log= LoggerFactory.getLogger(CreditRepositoryImpl.class);
    public CreditRepositoryImpl(CreditRepositoryMongoDB creditRepositoryMongoDB) {
        this.creditRepositoryMongoDB = creditRepositoryMongoDB;
    }

    @Override
    public Flux<CreditDTO> getListCredit() {
        return this.creditRepositoryMongoDB.findAll().map(Convert::entityToDto);
    }

    @Override
    public Mono<CreditDTO> getCreditById(String id) {
        return this.creditRepositoryMongoDB.findById(id).map(Convert::entityToDto);
    }

    @Override
    public Mono<CreditDTO> saveCredit(Mono<CreditDTO> creditDTOMono) {
        return creditDTOMono.map(Convert::DtoToEntity)
                .flatMap(creditRepositoryMongoDB::insert)
                .map(Convert::entityToDto);
    }

    @Override
    public Mono<CreditDTO> updateCredit(Mono<CreditDTO> creditDTOMono, String idclient,String idproduct) {
        return creditRepositoryMongoDB.findById(idclient)
                .flatMap(p -> creditDTOMono.map(Convert::DtoToEntity)
                        .doOnNext(e ->
                                e.setId(idclient)
                        ))
                .flatMap(creditRepositoryMongoDB::save)
                .map(Convert::entityToDto);
    }
    private Mono<CreditDTO> updateCreditLocal(Mono<CreditDTO> creditDTOMono, String id) {
        return creditRepositoryMongoDB.findById(id)
                .flatMap(p -> creditDTOMono.map(Convert::DtoToEntity)
                        .doOnNext(e ->
                                e.setId(id)
                        ))
                .flatMap(creditRepositoryMongoDB::save)
                .map(Convert::entityToDto);
    }

    @Override
    public Mono<Void> deleteCreditById(String id) {
        return creditRepositoryMongoDB.deleteById(id);
    }

    @Override
    public Flux<CreditDTO> getListByIdClient(String idClient) {
        return creditRepositoryMongoDB.findByIdClient(idClient);
    }

    @Override
    public Flux<CreditDTO> getListCreditByIdClientAndIdProduct(String idClient, String idProduct) {
        return creditRepositoryMongoDB.findByIdClientAndIdProduct(idClient,idProduct);
    }
   @Override
    public Mono<CreditDTO> getListCreditByIdClientAndIdTypeAndIdProduct(String idClient, String idType, String idProduct) {


      /* creditDTOMono.flatMap(c->
                   creditDTOMono.map(Convert::DtoToEntity)
       ).doOnNext(e->{
                   e.setId(e.getId());
       }).flatMap(creditRepositoryMongoDB::save)
         .map(Convert::entityToDto);

       */

        return creditRepositoryMongoDB.findByIdClientAndIdTypeAndIdProduct(idClient,idType,idProduct);
    }

}
