package com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api.request.BankTransactionValidate;

import reactor.core.publisher.Mono;

public interface BankTransactionValidatePort {

	Mono<Boolean>  validate(BankTransactionValidate bankTransactionValidate);
}
