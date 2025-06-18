package com.education.platform.model.notification;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notifications")
public class Notification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String userId;
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String message;
    
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    
    private boolean read;
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        read = false;
    }
    
    public enum NotificationType {
        REQUEST_CREATED,
        REQUEST_ASSIGNED,
        REQUEST_COMPLETED,
        REQUEST_CANCELLED,
        MESSAGE_RECEIVED,
        PAYMENT_RECEIVED,
        PAYMENT_REFUNDED
    }
} 