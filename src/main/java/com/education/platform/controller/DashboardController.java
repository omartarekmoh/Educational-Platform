package com.education.platform.controller;

import com.education.platform.dto.DashboardStatsDTO;
import com.education.platform.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasRole('STUDENT') and authentication.principal.id == #studentId")
    public ResponseEntity<DashboardStatsDTO> getStudentDashboard(@PathVariable Long studentId) {
        return ResponseEntity.ok(dashboardService.getStudentDashboard(studentId));
    }

    @GetMapping("/engineer/{engineerId}")
    @PreAuthorize("hasRole('ENGINEER') and authentication.principal.id == #engineerId")
    public ResponseEntity<DashboardStatsDTO> getEngineerDashboard(@PathVariable Long engineerId) {
        return ResponseEntity.ok(dashboardService.getEngineerDashboard(engineerId));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DashboardStatsDTO> getAdminDashboard() {
        return ResponseEntity.ok(dashboardService.getAdminDashboard());
    }
} 