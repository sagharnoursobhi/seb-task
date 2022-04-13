package com.example.demo.controller;

import com.example.demo.producer.KafkaBalanceProducer;
import com.seb.customerspending.CustomerBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("spending")
public class CustomerSpendingController {

    @Value("${spring.kafka.topic.spending}")
    private String topic;

    @Autowired
    private KafkaBalanceProducer kafkaBalanceProducer;

    @PostMapping("/swish")
    public ResponseEntity<?> swish(@RequestBody CustomerBalance customerBalance) {//it represents the whole http response//status code,header.body
        kafkaBalanceProducer.sendMessage(topic, customerBalance);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @PostMapping("/card")
    public ResponseEntity<?> creditCard(@RequestBody CustomerBalance customerBalance) {
        kafkaBalanceProducer.sendMessage(topic, customerBalance);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }
}
