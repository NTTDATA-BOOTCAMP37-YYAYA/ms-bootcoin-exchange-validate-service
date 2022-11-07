package com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing.FindWalletByIdPort;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api.enums.HttpStatusCodes;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api.response.ResponseWallet;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api.response.Wallet;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

/**.*/
@Component
public class ClientWalletAdapter implements FindWalletByIdPort {

  final  Logger logger = LoggerFactory.getLogger(ClientWalletAdapter.class);

  @Value("${client.bootcoin.wallet.url}")
  private String url;

  private WebClient client = WebClient.create(url);

@Override
@CircuitBreaker(name = "", fallbackMethod = "findWalletByIdPortAlternative")
public Mono<Wallet> findWalletByIdPort(String walletId) {
	 Mono<Wallet> response = client.get()
		        .uri(url.concat("/{walletId}"), walletId)
		        .retrieve()
		        .onStatus(HttpStatus::is4xxClientError, clientResponse -> 
		        Mono.error(new Exception("Error 400 findWalletByIdPort")))
		        .onStatus(HttpStatus::is5xxServerError, clientResponse ->  
		        Mono.error(new Exception("Error 500 findWalletByIdPort")))
		        .toEntity(ResponseWallet.class)
		        .flatMap(r -> r.getBody().getHttpStatus() == HttpStatusCodes.STATUS_NO_FOUND.getValue() 
		            ? Mono.error(new Exception("Error 404 findWalletByIdPort"))
		            : Mono.just(r.getBody().getWallet()));
		    return response;
}

public Mono<Wallet> findWalletByIdPortAlternative(String debitCardNumber, Exception e) {
    return Mono.error(new Exception("Error on findWalletByIdPort"));
  }



}
