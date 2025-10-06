package com.app.NotificationMicroService.service.impl;

import com.app.NotificationMicroService.entity.Notification;
import com.app.NotificationMicroService.repository.NotificationRepository;
import com.app.NotificationMicroService.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}
