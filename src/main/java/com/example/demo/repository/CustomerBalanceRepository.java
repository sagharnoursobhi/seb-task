package com.example.demo.repository;

import com.example.demo.dto.CustomerBalanceDto;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class CustomerBalanceRepository {

  ConcurrentHashMap<String, Double> balance = new ConcurrentHashMap<>();//data structure hashtable. multiple thread can
  //perform simultaneously. Null is not allowed for both value and key.

  public void createCustomer(final CustomerBalanceDto customerBalanceDto) {
    balance.put(customerBalanceDto.getId(), customerBalanceDto.getAmount());
  }

  public void rebalance(final CustomerBalanceDto customerBalanceDto) {
    // first we find the customer balance by its key
    Double currentBalance = balance.get(customerBalanceDto.getId());
    if (currentBalance == null) {
      throw new RuntimeException("Customer missing");
    }
    Double updatedBalance = currentBalance + customerBalanceDto.getAmount();
    balance.put(customerBalanceDto.getId(), updatedBalance);
  }

}
