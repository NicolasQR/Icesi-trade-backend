package com.example.icesitrade.service;

import com.example.icesitrade.model.Notification;

import java.util.List;

public interface NotificationService {
    Notification createNotification(Notification notification);
    List<Notification> getNotificationsByUser(Long userId);
    void markAsRead(Long id);
}
