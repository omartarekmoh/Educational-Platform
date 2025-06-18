package com.education.platform.controller;

import com.education.platform.model.request.AssignmentRequest;
import com.education.platform.model.request.ProjectRequest;
import com.education.platform.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/student")
@PreAuthorize("hasRole('STUDENT')")
public class StudentDashboardController {

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> getProfile() {
        // TODO: Implement get student profile
        return ResponseEntity.ok(new ApiResponse(true, "Profile retrieved successfully", null));
    }

    @PostMapping("/assignment/request")
    public ResponseEntity<ApiResponse> requestAssignment(
            @RequestParam("file") MultipartFile file,
            @ModelAttribute AssignmentRequest request) {
        // TODO: Implement assignment request
        return ResponseEntity.ok(new ApiResponse(true, "Assignment request submitted successfully", null));
    }

    @PostMapping("/project/request")
    public ResponseEntity<ApiResponse> requestProject(
            @RequestParam("file") MultipartFile file,
            @ModelAttribute ProjectRequest request) {
        // TODO: Implement project request
        return ResponseEntity.ok(new ApiResponse(true, "Project request submitted successfully", null));
    }

    @GetMapping("/requests")
    public ResponseEntity<ApiResponse> getRequests() {
        // TODO: Implement get student requests
        return ResponseEntity.ok(new ApiResponse(true, "Requests retrieved successfully", null));
    }

    @GetMapping("/notifications")
    public ResponseEntity<ApiResponse> getNotifications() {
        // TODO: Implement get student notifications
        return ResponseEntity.ok(new ApiResponse(true, "Notifications retrieved successfully", null));
    }
} 