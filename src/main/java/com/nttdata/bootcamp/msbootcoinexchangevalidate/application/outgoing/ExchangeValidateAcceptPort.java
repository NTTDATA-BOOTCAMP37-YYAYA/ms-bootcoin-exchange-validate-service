package com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing;

import reactor.core.publisher.Mono;


public interface ExchangeValidateAcceptPort {

	Mono<Boolean> validateTransactionBootcoin(
			String exchangePublishTypePayId,
			String exchangePublishYankiCellPhone,
			String exchangePublishAccount,
			String exchangeApplyForCellphone, 
			String exchangeApplyForBank, 
			String exchangeApplyForAccount,
			String exchangeApplyForQuantity);
	
}
