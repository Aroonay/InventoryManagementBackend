package com.demo.ProductService.service.impl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisherService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaPublisherService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLowStockEvent(String message) {
        kafkaTemplate.send("low-stock-topic", message);
    }

    public void sendOutOfStockEvent(String message) {
        kafkaTemplate.send("out-of-stock-topic", message);
    }
}
