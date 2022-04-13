package com.example.demo.producer;

import com.seb.customerspending.CustomerBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaBalanceProducer {

    @Autowired
    private KafkaTemplate<String, CustomerBalance> kafkaTemplate;


    public void sendMessage(String topic, CustomerBalance customerBalance) {
        log.info(String.format("#### -> Producing message -> %s", customerBalance.toString()));
        this.kafkaTemplate.send(topic, customerBalance);
    }
}
