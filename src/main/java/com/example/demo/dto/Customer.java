package com.example.demo.dto;


public class Customer {

    private String customerId;
    private Double initialAmount;

    public Customer(String customerId, Double initialAmount) {
        this.customerId = customerId;
        this.initialAmount = initialAmount;
    }


    public String getCustomerId() {
        return customerId;
    }

    public Double getInitialAmount() {
        return initialAmount;
    }


    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setInitialAmount(Double initialAmount) {
        this.initialAmount = initialAmount;
    }
}
