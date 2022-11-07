package com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**.*/
@AllArgsConstructor
@NoArgsConstructor
public enum TypePayId {

  TRANSFER("T"),
  YANKI("Y");
  
  private String value;

  public String getValue() {
    return value;
  }



}
