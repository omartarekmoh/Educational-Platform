package com.education.platform.security.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String fullName;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;
    
    @Column(nullable = false)
    private boolean active = true;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;
    
    private String university;
    private String faculty;
    private String department;
    private String academicLevel;
    private String studyLocation;
    
    @Column(columnDefinition = "TEXT")
    private String qualifications;
    
    @Column(columnDefinition = "TEXT")
    private String experience;
    
    private Double rating;
    
    public enum UserType {
        STUDENT,
        ENGINEER,
        ADMIN
    }
} 