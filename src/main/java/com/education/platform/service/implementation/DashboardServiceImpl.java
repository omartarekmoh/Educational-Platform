package com.education.platform.service.implementation;

import com.education.platform.dto.DashboardStatsDTO;
import com.education.platform.service.CourseService;
import com.education.platform.service.DashboardService;
import com.education.platform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserService userService;
    private final CourseService courseService;

    @Override
    public DashboardStatsDTO getStudentDashboard(Long studentId) {
        return DashboardStatsDTO.builder()
                .totalCourses((long) courseService.getStudentCourses(studentId).size())
                .recentActivities(new ArrayList<>())
                .topCourses(new ArrayList<>())
                .enrollmentStats(new HashMap<>())
                .build();
    }

    @Override
    public DashboardStatsDTO getEngineerDashboard(Long engineerId) {
        return DashboardStatsDTO.builder()
                .totalCourses((long) courseService.getEngineerCourses(engineerId).size())
                .recentActivities(new ArrayList<>())
                .topCourses(new ArrayList<>())
                .enrollmentStats(new HashMap<>())
                .build();
    }

    @Override
    public DashboardStatsDTO getAdminDashboard() {
        return DashboardStatsDTO.builder()
                .totalCourses((long) courseService.getAllCourses().size())
                .totalStudents(0L)
                .totalRevenue(0L)
                .recentActivities(new ArrayList<>())
                .topCourses(new ArrayList<>())
                .enrollmentStats(new HashMap<>())
                .paymentStats(new HashMap<>())
                .build();
    }
} 