package com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.incoming.ExchangeValidateAcceptUseCase;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.incoming.FindTransactionByNumberUseCase;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.incoming.UpdateTransactionUseCase;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing.BankTransactionValidatePort;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing.FindTransactionByNumberPort;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing.FindWalletByIdPort;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing.InsertTransactionPort;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing.SendTransactionBankPort;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing.UpdateTransactionPort;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.application.outgoing.YankiTransactionValidatePort;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.enums.TypeBankId;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.enums.TypePayId;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.enums.TypeTransactionBankId;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.BankTransaction;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.Exchange;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.Transaction;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.ValidateExchange;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api.request.BankTransactionValidate;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api.request.YankiTransactionValidate;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api.response.Wallet;

import reactor.core.publisher.Mono;

/**.*/
@Service
public class ExchangeValidateService implements ExchangeValidateAcceptUseCase,
												UpdateTransactionUseCase,
												FindTransactionByNumberUseCase{

final  Logger logger = LoggerFactory.getLogger(ExchangeValidateService.class);

@Autowired
private FindWalletByIdPort findWalletByIdPort;

@Autowired
private BankTransactionValidatePort bankTransactionValidatePort;

@Autowired
private YankiTransactionValidatePort yankiTransactionValidatePort;

@Autowired
private InsertTransactionPort insertTransactionPort;

@Autowired
private SendTransactionBankPort sendTransactionBankPort;

@Autowired
private UpdateTransactionPort updateTransactionPort;

@Autowired
private FindTransactionByNumberPort findTransactionByNumberPort;


@Override
public Mono<Boolean> validateExchange(Exchange exchange) {
	
	Transaction transaction = new Transaction();
	transaction.setTransactionNumber(exchange.getExchangeTransactionNumber());
	transaction.setExchangeId(exchange.getExchangeId());
	transaction.setExchangePublishTypePayId(exchange.getExchangePublishTypePayId());
	
	
	
    Mono<Optional<Wallet>>  MonoWalletPublish = findWalletByIdPort.findWalletByIdPort(exchange.getWalletExchangePublishId()).map(Optional::of);
    Mono<Optional<Wallet>>  MonoWalletApplyFor = findWalletByIdPort.findWalletByIdPort(exchange.getWalletExchangeApplyForId()).map(Optional::of);
    
    ValidateExchange validateExchange =  new ValidateExchange();
    validateExchange.setValidate(Boolean.TRUE);
    Mono<ValidateExchange> monoValidateExchange=Mono.just(validateExchange);
    		/*Mono.zip(MonoWalletPublish, MonoWalletApplyFor)
    				.map(tupla -> {
    					 StrBuilder message = new StrBuilder();
    					 Boolean validateWalletPublish = Boolean.FALSE;
    				     Boolean validateWalletApplyFor = Boolean.FALSE;
    					 if(tupla.getT1().isPresent()&&!tupla.getT1().isEmpty()) {
    						 validateWalletPublish=Boolean.TRUE;
    					 }
    					 message.append(validateWalletPublish.equals(Boolean.FALSE) 
    					                ? " Wallet Exchange Publish not found"
    					                : "");
    					 if(tupla.getT2().isPresent()&&!tupla.getT2().isEmpty()) {
    						 validateWalletApplyFor=Boolean.TRUE;
    					 }
    					 message.append(validateWalletApplyFor.equals(Boolean.FALSE) 
    					                ? " Wallet Exchange Apply for not found"
    					                : "");
    					 
    					 ValidateExchange validate= new ValidateExchange(validateWalletPublish&&validateWalletApplyFor,message.toString());
    					 return validate;
    					 
    				});*/
    		
    
     Mono<Boolean> validateTransaction = monoValidateExchange.flatMap(validateBootCoinAccount ->{

			if (validateBootCoinAccount.getValidate()&&exchange.getExchangePublishTypePayId().equals(TypePayId.TRANSFER.getValue())) {
				
				boolean validateSourceAccount=Boolean.FALSE;
				if (exchange.getExchangeApplyForBank().equals(TypeBankId.BOOTCAMPBANK.getValue())) {
					validateSourceAccount= Boolean.TRUE;
				}
				
				BankTransactionValidate validateBank = new BankTransactionValidate(exchange.getExchangeApplyForAccount(),
															exchange.getExchangePublishAccount(),
															exchange.getExchangeApplyForAmount(),
															validateSourceAccount,
															Boolean.TRUE);
				return  
						bankTransactionValidatePort.validate(validateBank);
				
			}
			
			else if (validateBootCoinAccount.getValidate()&&exchange.getExchangePublishTypePayId().equals(TypePayId.YANKI.getValue())) {
	
				
				YankiTransactionValidate validateYanki = new YankiTransactionValidate(exchange.getExchangeApplyForYankiCellphone(),
															 exchange.getExchangePublishYankiCellPhone(),
															 exchange.getExchangeApplyForAmount(),
															 true);
				return yankiTransactionValidatePort.validate(validateYanki);
				
			}else {
				return Mono.just(Boolean.FALSE);
			}
    
    	   });

     return 
    		 
    		 insertTransactionPort.insertTransaction(transaction)
    		 .flatMap(newTransaction -> validateTransaction.flatMap(validateT -> {
        	    	 if(validateT) {
        	    		 if (exchange.getExchangePublishTypePayId().equals(TypePayId.TRANSFER.getValue())) {
        	    			 
        	    			 BankTransaction bankTransaction = new BankTransaction();
        	    			 bankTransaction.setTransactionTypeId(TypeTransactionBankId.BOOTCOIN.getValue());
        	    			 bankTransaction.setTransactionAmount(exchange.getExchangeApplyForAmount());
        	    			 bankTransaction.setTransactionCurrency("S");
        	    			 bankTransaction.setTransactionNumberOrigen(newTransaction.getTransactionNumber());
        	    			 bankTransaction.setAccountSource(exchange.getExchangeApplyForAccount());
        	    			 bankTransaction.setAccountDestiny(exchange.getExchangePublishAccount());
        	    			 //falta tipo de cambio
        	    			 
        	    			 sendTransactionBankPort.sendTransactionBank(bankTransaction);
        	    		 }
        	    		 else if (exchange.getExchangePublishTypePayId().equals(TypePayId.YANKI.getValue())) {
        	    			 //manda a cola de transferencia con el codigo de transferencia
        	    		 }
        	    	 }
        	    	 return Mono.just(validateT);
        	     }))
    		 .defaultIfEmpty(Boolean.FALSE);
}

@Override
public Mono<Transaction> updateTransaction(Transaction transaction,Transaction transactionUpdate) {
	return updateTransactionPort.updateTransaction(transaction, transactionUpdate);
}

@Override
public Mono<Transaction> findTransactionByNumberPort(String transactionNumber) {
	return findTransactionByNumberPort.findTransactionByNumberPort(transactionNumber);
}
  




}
