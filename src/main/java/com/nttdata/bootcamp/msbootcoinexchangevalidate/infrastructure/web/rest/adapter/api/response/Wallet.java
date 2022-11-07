package com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.web.rest.adapter.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
  
  private String walletId;
  private String customerId;
  private String customerCellPhone;
  private String customerEmail;
  private String walletState;
  private String walletCreateDate;


}
