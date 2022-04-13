package com.example.demo.consumer;

import com.example.demo.dto.CustomerBalanceDto;
import com.example.demo.repository.CustomerBalanceRepository;
import com.seb.customerspending.CustomerBalance;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableKafka
public class CustomerSpendingConsumer {

  @Autowired
  private CustomerBalanceRepository customerBalanceRepository;

  @KafkaListener(topics = "customer-spending")
  public void listen(ConsumerRecord<String, CustomerBalance> record) {
    //customerBalanceRepository.rebalance(customerBalanceDto);
    System.out.println(record.value());
  }
}
