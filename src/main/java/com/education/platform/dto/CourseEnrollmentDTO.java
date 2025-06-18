package com.education.platform.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseEnrollmentDTO {
    @NotNull(message = "Student ID is required")
    private Long studentId;
} 