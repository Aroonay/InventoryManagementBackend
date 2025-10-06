package com.app.NotificationMicroService.service;

import com.app.NotificationMicroService.entity.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getAllNotifications();
}
