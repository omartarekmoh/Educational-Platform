package com.education.platform.service;

import com.education.platform.model.notification.Notification;
import java.util.List;

public interface NotificationService {
    Notification createNotification(Notification notification);
    List<Notification> getUserNotifications(String userId);
    List<Notification> getUnreadNotifications(String userId);
    void markAsRead(Long notificationId);
    void markAllAsRead(String userId);
    void deleteNotification(Long notificationId);
    void sendNotification(String userId, String title, String message, Notification.NotificationType type);
} 