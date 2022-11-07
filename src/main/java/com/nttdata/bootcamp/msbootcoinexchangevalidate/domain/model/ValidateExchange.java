package com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateExchange {

  private Boolean validate;
  private String message;
}
