package com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.persistence.adapter.mongo;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.persistence.adapter.mongo.entity.TransactionEntity;

import reactor.core.publisher.Mono;

public interface ReactiveMongoTransactionRepository extends ReactiveMongoRepository<TransactionEntity, String>{

	  
	  @Query("{'transactionNumber': ?0}")
	  public Mono<TransactionEntity> findTransactionByNumber(String transactionNumber);
 

}