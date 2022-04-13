package com.example.demo.producer;

import com.example.demo.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaCustomerProducer {


    @Autowired
    KafkaTemplate<String, Customer> kafkaTemplate;

    public void sendToKafka(String topic , Customer customer) {
        log.info(String.format("Customer: %s", customer));
        kafkaTemplate.send(topic, customer);
    }
}
