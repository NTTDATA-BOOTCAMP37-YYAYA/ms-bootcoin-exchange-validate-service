package com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YankiTransactionValidate {

	
	private String yankiAccountCellPhoneSendsPay;
	private String yankiAccountCellPhoneReceivesPay;
	private double yankiTransactionAmount;
	private boolean yankiTransactionPayWithAccountBank;
}
