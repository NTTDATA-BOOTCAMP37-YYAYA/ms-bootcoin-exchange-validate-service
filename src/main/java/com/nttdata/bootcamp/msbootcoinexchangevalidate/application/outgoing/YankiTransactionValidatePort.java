package com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api.request.YankiTransactionValidate;

import reactor.core.publisher.Mono;

public interface YankiTransactionValidatePort {

	Mono<Boolean>  validate(YankiTransactionValidate yankiTransactionValidate);
}
