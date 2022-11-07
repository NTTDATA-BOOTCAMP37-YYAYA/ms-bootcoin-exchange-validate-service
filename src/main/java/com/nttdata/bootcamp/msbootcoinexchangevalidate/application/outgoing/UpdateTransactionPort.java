package com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.Transaction;

import reactor.core.publisher.Mono;

public interface UpdateTransactionPort {

	Mono<Transaction> updateTransaction(Transaction transaction, Transaction transactionUpdate);
}
