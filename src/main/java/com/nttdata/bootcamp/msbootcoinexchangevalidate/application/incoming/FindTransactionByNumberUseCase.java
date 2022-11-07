package com.nttdata.bootcamp.msbootcoinexchangevalidate.application.incoming;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.Transaction;

import reactor.core.publisher.Mono;

public interface FindTransactionByNumberUseCase {

	Mono<Transaction>  findTransactionByNumberPort(String transactionNumber);
}
