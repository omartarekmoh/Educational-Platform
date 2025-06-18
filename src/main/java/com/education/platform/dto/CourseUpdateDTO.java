package com.education.platform.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseUpdateDTO {
    private String title;
    private String description;
    
    @Positive(message = "Price must be positive")
    private BigDecimal price;
} 