package com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exchange {
	
	private String exchangeId;
	private String customerId;
	private String walletExchangePublishId;
	private double exchangePublishQuantityAllowed;
	private String exchangePublishDate;
	private String exchangePublishYankiCellPhone;
	private String exchangePublishAccount;
	private String exchangePublishTypePayId;
	
	
	private String walletExchangeApplyForId;
	private String exchangeApplyForYankiCellphone;
	private String exchangeApplyForBank;
	private String exchangeApplyForAccount;
	private double exchangeApplyForAmount;
	private String exchangeApplyForDate;
	
	private String exchangeState;
	private String exchangeAcceptDate;
	private String exchangeTransactionNumber;

}
