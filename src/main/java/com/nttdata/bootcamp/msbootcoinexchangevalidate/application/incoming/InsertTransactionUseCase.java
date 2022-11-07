package com.nttdata.bootcamp.msbootcoinexchangevalidate.application.incoming;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.Transaction;

import reactor.core.publisher.Mono;

public interface InsertTransactionUseCase {

	Mono<Transaction> insertTransaction(Transaction transaction);
}
