package com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankTransaction {



  private String transactionId;
  private String transactionTypeId;
  private double transactionAmount;
  private String transactionCurrency;
  private long   transactionCommission;
  private String transactionNumberOrigen;
  private String accountSource;
  private String accountDestiny;
  private double transactionExchangeRate;
  
  
  
}
