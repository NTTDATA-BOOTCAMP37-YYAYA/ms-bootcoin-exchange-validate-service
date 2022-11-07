package com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.consumer.adapter.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.incoming.ExchangeValidateAcceptUseCase;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.incoming.FindTransactionByNumberUseCase;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.incoming.UpdateTransactionUseCase;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.enums.StatesTransaction;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.BankTransaction;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.Exchange;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.Transaction;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionConsumerAdapter {

  final  Logger logger = LoggerFactory.getLogger(TransactionConsumerAdapter.class);


  @Autowired
  private UpdateTransactionUseCase  updateTransactionUseCase;
  
  @Autowired
  private FindTransactionByNumberUseCase  findTransactionByNumberUseCase;
  
  @Autowired
  private ExchangeValidateAcceptUseCase  exchangeValidateAcceptUseCase;
  

  @KafkaListener(topics="${kafka.topic.bootcoin.transaction.update.name}",containerFactory="kafkaListenerContainerFactoryBankTransaction", groupId="")
  public void listenerUpdate(@Payload BankTransaction bankTransaction) {
	 
	  Transaction transaction = new Transaction();
	  transaction.setTransactionNumber(bankTransaction.getTransactionNumberOrigen());
	  transaction.setTransactionState(StatesTransaction.INSERTDESTINY.getValue());
	  transaction.setTransactionDestinyNumber(bankTransaction.getTransactionId());
	  findTransactionByNumberUseCase.findTransactionByNumberPort(bankTransaction.getTransactionNumberOrigen())
	  .map(findTransaction -> updateTransactionUseCase.updateTransaction(findTransaction,transaction)).block();
  }
  
  @KafkaListener(topics = "${kafka.topic.bootcoin.transaction.create.name}",containerFactory="kafkaListenerContainerFactoryExchange", groupId="")
  public void listenerValidate(@Payload Exchange exchange) {
	  exchangeValidateAcceptUseCase.validateExchange(exchange).block();
	  
  }


}