package com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.BankTransaction;

public interface SendTransactionBankPort {
	
	void sendTransactionBank(BankTransaction bankTransaction);

}
