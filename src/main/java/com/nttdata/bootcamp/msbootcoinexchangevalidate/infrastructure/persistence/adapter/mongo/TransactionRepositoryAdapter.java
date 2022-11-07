package com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.persistence.adapter.mongo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing.FindTransactionByNumberPort;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing.InsertTransactionPort;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing.UpdateTransactionPort;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.enums.StatesTransaction;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.Transaction;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.persistence.adapter.mongo.entity.TransactionEntity;

import reactor.core.publisher.Mono;

@Component
public class TransactionRepositoryAdapter implements InsertTransactionPort,
													 UpdateTransactionPort,
													 FindTransactionByNumberPort{



@Autowired
  private ReactiveMongoTransactionRepository reactiveMongoDB;



@Override
public Mono<Transaction> updateTransaction(Transaction transaction, Transaction transactionUpdate) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	LocalDateTime now = LocalDateTime.now();
	String updateDate = now.format(formatter);
	transaction.setTransactionUpdateDate(updateDate);
	transaction.setTransactionState(transactionUpdate.getTransactionState());
	transaction.setTransactionState(transactionUpdate.getTransactionDestinyNumber());
	
	
	return reactiveMongoDB.save(TransactionEntity.toTransactionEntity(transaction))
			 .map(TransactionEntity::toTransaction);
}

@Override
public Mono<Transaction> insertTransaction(Transaction transaction) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	LocalDateTime now = LocalDateTime.now();
    String createDate = now.format(formatter);
    transaction.setTransactionCreateDate(createDate);
    transaction.setTransactionState(StatesTransaction.CREATED.getValue());
	return reactiveMongoDB.insert(TransactionEntity.toTransactionEntity(transaction))
			  .map(TransactionEntity::toTransaction);
}

@Override
public Mono<Transaction> findTransactionByNumberPort(String transactionNumber) {
	return reactiveMongoDB.findTransactionByNumber(transactionNumber).map(TransactionEntity::toTransaction);
}

}
