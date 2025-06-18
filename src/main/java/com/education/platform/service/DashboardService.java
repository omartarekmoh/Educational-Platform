package com.education.platform.service;

import com.education.platform.dto.DashboardStatsDTO;

public interface DashboardService {
    DashboardStatsDTO getStudentDashboard(Long studentId);
    DashboardStatsDTO getEngineerDashboard(Long engineerId);
    DashboardStatsDTO getAdminDashboard();
} 