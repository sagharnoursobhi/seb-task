package com.example.demo.service;

import com.example.demo.dto.CustomerBalanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

//@Service
public class CustomerService {

    @Autowired
    private CustomerBalanceDto customerBalance;

    @Bean
    public CustomerBalanceDto getCustomer() {
        return customerBalance;
    }
}

