package com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.Transaction;

import reactor.core.publisher.Mono;

public interface FindTransactionByNumberPort {

	Mono<Transaction>  findTransactionByNumberPort(String transactionNumber);
}
