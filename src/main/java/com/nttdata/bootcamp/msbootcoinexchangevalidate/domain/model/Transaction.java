package com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

	private String transactionId;
	private String exchangeId;
	private String exchangePublishTypePayId;
	private String transactionNumber;
	private String transactionCreateDate;
	private String transactionState;
	private String transactionUpdateDate;
	private String transactionDestinyNumber;
	
}
