package com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**.*/
@AllArgsConstructor
@NoArgsConstructor
public enum TypeBankId {

  BOOTCAMPBANK("B"),
  OTHERS("O");
  
  private String value;

  public String getValue() {
    return value;
  }



}
