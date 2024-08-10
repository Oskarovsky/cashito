package com.slyko.cashitoinfra.adapter.secondary.messaging.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

//@Component
public class KafkaMessageInitializer {

    private final KafkaMessageProducer kafkaMessageProducer;

    @Autowired
    public KafkaMessageInitializer(KafkaMessageProducer kafkaMessageProducer) {
        this.kafkaMessageProducer = kafkaMessageProducer;
    }

    @PostConstruct
    public void sendInitialMessage() {
        try {
            String message = "Initial Message at Startup";
            kafkaMessageProducer.sendMessage(message);
            System.out.println("Sent initial message to Kafka: " + message);
        } catch (Exception e) {
            System.err.println("Failed to send initial message to Kafka: " + e.getMessage());
        }
    }
}