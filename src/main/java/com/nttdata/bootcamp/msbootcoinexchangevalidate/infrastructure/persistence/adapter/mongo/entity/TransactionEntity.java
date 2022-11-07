package com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.persistence.adapter.mongo.entity;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "Exchange")
public class TransactionEntity {

	@Id
	private String transactionId;
	private String exchangeId;
	private String exchangePublishTypePayId;
	private String transactionNumber;
	private String transactionCreateDate;
	private String transactionState;
	private String transactionUpdateDate;
	private String transactionDestinyNumber;
	
	  /**.*/
	  public static Transaction toTransaction(TransactionEntity transactionEntity) {
		Transaction transaction = new Transaction();
	    BeanUtils.copyProperties(transactionEntity, transaction);
	    return transaction;
	  }
	  
	  /**.*/
	  public static TransactionEntity toTransactionEntity(Transaction transaction) {
		TransactionEntity transactionEntity = new TransactionEntity();
	    BeanUtils.copyProperties(transaction, transactionEntity);
	    return transactionEntity;
	  }
	
}
