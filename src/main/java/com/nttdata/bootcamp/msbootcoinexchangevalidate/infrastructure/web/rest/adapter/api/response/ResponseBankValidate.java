package com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBankValidate {
  
  private int httpStatus;
  private Boolean validate;
  private String message;

}