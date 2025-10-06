package com.app.NotificationMicroService.service.impl;


import com.app.NotificationMicroService.entity.Notification;
import com.app.NotificationMicroService.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KafkaConsumerService {

    @Autowired
    private NotificationRepository notificationRepository;


    @KafkaListener(topics = "low-stock-topic", groupId = "notification-group")
    public void consume(String message) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setType("LOW_STOCK");
        notification.setCreatedAt(LocalDateTime.now());
        notification.setReadStatus(false);

        notificationRepository.save(notification);
        System.out.println("📩 Notification saved: " + message);
    }
}