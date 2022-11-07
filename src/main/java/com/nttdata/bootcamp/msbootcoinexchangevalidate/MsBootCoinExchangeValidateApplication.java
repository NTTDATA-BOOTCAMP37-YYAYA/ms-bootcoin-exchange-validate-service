package com.nttdata.bootcamp.msbootcoinexchangevalidate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**.*/
@SpringBootApplication
@EnableEurekaClient
public class MsBootCoinExchangeValidateApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsBootCoinExchangeValidateApplication.class, args);
  }

}
