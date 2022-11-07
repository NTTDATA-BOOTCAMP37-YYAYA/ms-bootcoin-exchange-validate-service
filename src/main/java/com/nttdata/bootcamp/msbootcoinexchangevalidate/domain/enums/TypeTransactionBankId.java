package com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**.*/
@AllArgsConstructor
@NoArgsConstructor
public enum TypeTransactionBankId {

  BOOTCOIN("B");
	
	
  private String value;

  public String getValue() {
    return value;
  }



}
