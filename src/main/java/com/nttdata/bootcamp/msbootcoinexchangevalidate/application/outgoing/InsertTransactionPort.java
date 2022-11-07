package com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.Transaction;

import reactor.core.publisher.Mono;

public interface InsertTransactionPort {

	Mono<Transaction> insertTransaction(Transaction transaction);
}
