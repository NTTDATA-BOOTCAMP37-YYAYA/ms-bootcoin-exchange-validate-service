package com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**.*/
@AllArgsConstructor
@NoArgsConstructor
public enum StatesTransaction {

  CREATED("C"),
  INSERTDESTINY("I"),
  UPDATEORIGIN("U");
	
	
  private String value;

  public String getValue() {
    return value;
  }



}
