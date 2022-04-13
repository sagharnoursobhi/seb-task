package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder//it is used to initializing the object
public class CustomerBalanceDto {

  private final String id;
  private final Double amount;

}

