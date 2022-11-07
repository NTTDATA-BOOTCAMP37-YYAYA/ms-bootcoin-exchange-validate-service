package com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.producer.adapter.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing.SendTransactionBankPort;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.BankTransaction;

import lombok.RequiredArgsConstructor;

/**.*/
@Component
@RequiredArgsConstructor
public class TransactionBankProducerAdapter implements SendTransactionBankPort {
  
  final  Logger logger = LoggerFactory.getLogger(TransactionBankProducerAdapter.class);
  
  @Value("${kafka.topic.bank.transaction.bootcoin-create.name}")
  private String topic;

  private  final KafkaTemplate<String, BankTransaction> kafkaTemplate;
  
  @Override
  public void sendTransactionBank(BankTransaction bankTransaction) {
    
    ListenableFuture<SendResult<String, BankTransaction>> future = kafkaTemplate.send(this.topic, bankTransaction);
    
    future.addCallback(new ListenableFutureCallback<SendResult<String, BankTransaction>>() {

      @Override
      public void onSuccess(SendResult<String, BankTransaction> result) {
        logger.info("Message {} has been sent", result);
      }

      @Override
      public void onFailure(Throwable ex) {
        logger.error("Something went wrong with the bank transaction");
        
      }

    });
  }

  

}
