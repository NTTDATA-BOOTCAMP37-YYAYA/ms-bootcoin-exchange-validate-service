package com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api.response.Wallet;

import reactor.core.publisher.Mono;

public interface FindWalletByIdPort {

	Mono<Wallet>  findWalletByIdPort(String walletId);
}
