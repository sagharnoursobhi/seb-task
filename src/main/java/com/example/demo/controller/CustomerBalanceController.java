package com.example.demo.controller;

import com.example.demo.dto.Customer;
import com.example.demo.producer.KafkaCustomerProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("balance")
public class CustomerBalanceController {

    public int accountNumber = 1000;

    @Autowired
    KafkaCustomerProducer kafkaCustomerProducer;

    @Value("${spring.kafka.topic.balance}")
    private String topic;

    private final ArrayList<Customer> customers = new ArrayList<>();
  /**
   * Post REST point for creating a customer
   */
  Map<String,Customer> mapper = new HashMap<>();

  @PostMapping("/customer")
  public ResponseEntity<?> customer(@RequestBody Customer customer) {
      kafkaCustomerProducer.sendToKafka(topic, customer);
      System.out.println(customer);
    //checkCustomer(customer);
    return ResponseEntity.ok(HttpEntity.EMPTY);
  }

  public void checkCustomer(Customer customer) {
      for (Customer element: this.customers) {
          if (element == customer) {
              break;
          } else
              this.customers.add(element);
      }
  }
  // createCustomer

  /**
   * Post REST point for credit cards deductibles
   */
  // put credit card spending

  /**
   * Post REST point for Swish payments
   */
  // put swish spending or receiving

}
