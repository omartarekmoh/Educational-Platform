package com.education.platform.model.entity;

import com.education.platform.security.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "requests")
public class Request {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;
    
    @ManyToOne
    @JoinColumn(name = "engineer_id")
    private User engineer;
    
    @Enumerated(EnumType.STRING)
    private RequestType type;
    
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private String subject;
    private String deadline;
    private String studyLocation;
    
    @Column(columnDefinition = "TEXT")
    private String additionalNotes;
    
    private String fileUrl;
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum RequestType {
        ASSIGNMENT,
        PROJECT,
        TUTORING
    }
    
    public enum RequestStatus {
        PENDING,
        ASSIGNED,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }
} 