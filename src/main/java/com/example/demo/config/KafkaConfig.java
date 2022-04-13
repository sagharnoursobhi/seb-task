package com.example.demo.config;


import com.seb.customerspending.CustomerBalance;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaConfig {


   @Value("${spring.kafka.bootstrap.server}")
   private String bootstrapServer;

   @Bean
    public ProducerFactory<String, CustomerBalance> producerFactory() {//producer factory is an interface and is used to set up
        // kafka producer configuration used in kafka templates later
        //kafka producer
       Map<String,Object> props = new HashMap<>();
       props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
       props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
       props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
       props.put("schema.registry.url", "http://localhost:8081");
        return new DefaultKafkaProducerFactory<>(props);
    }


    @Bean
    public KafkaTemplate<String, CustomerBalance> kafkaTemplate() { return new KafkaTemplate<>(producerFactory());}

    @Bean
    public ConsumerFactory<String, CustomerBalance> consumerFactory() {
        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group6");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put("schema.registry.url", "http://localhost:8081");
        return new DefaultKafkaConsumerFactory<>(props);//created bean
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CustomerBalance> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CustomerBalance> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
//        factory.setBatchListener(true);
//        factory.getContainerProperties().setPollTimeout(1000);
//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
//        factory.getContainerProperties().setSyncCommits(true);
        return factory;//creating container for kafka listener
    }
    // create consumer config

}
