package com.nttdata.bootcamp.msbootcoinexchangevalidate.application.incoming;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.Exchange;

import reactor.core.publisher.Mono;


public interface ExchangeValidateAcceptUseCase {

	Mono<Boolean> validateExchange(Exchange exchange);
	
}
