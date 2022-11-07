package com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing.BankTransactionValidatePort;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api.enums.HttpStatusCodes;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api.request.BankTransactionValidate;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api.response.ResponseBankValidate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

/**.*/
@Component
public class ClientBankTransactionValidate implements BankTransactionValidatePort {

  final  Logger logger = LoggerFactory.getLogger(ClientBankTransactionValidate.class);

  @Value("${client.bank.transaction-validate.url}")
  private String url;

  private WebClient client = WebClient.create(url);



@Override
@CircuitBreaker(name = "", fallbackMethod = "validateAlternative")
public Mono<Boolean> validate(BankTransactionValidate bankTransactionValidate) {
	 Mono<Boolean> response = client.get()
		        .uri(url,bankTransactionValidate)
		        .retrieve()
		        .onStatus(HttpStatus::is4xxClientError, clientResponse -> 
		        Mono.error(new Exception("Error 400 validate")))
		        .onStatus(HttpStatus::is5xxServerError, clientResponse ->  
		        Mono.error(new Exception("Error 500 validate")))
		        .toEntity(ResponseBankValidate.class)
		        .flatMap(r -> r.getBody().getHttpStatus() == HttpStatusCodes.STATUS_NO_FOUND.getValue() 
		            ? Mono.error(new Exception("Error 404 validate"))
		            : Mono.just(r.getBody().getValidate()));
		    return response;
}

public Mono<Boolean> validateAlternative(String bankTransactionValidate, Exception e) {
    return Mono.error(new Exception("Error on validate"));
  }


}
