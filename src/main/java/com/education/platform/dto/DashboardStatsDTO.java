package com.education.platform.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class DashboardStatsDTO {
    private Long totalCourses;
    private Long totalStudents;
    private Long totalRevenue;
    private List<Map<String, Object>> recentActivities;
    private List<Map<String, Object>> topCourses;
    private Map<String, Long> enrollmentStats;
    private Map<String, Long> paymentStats;
} 