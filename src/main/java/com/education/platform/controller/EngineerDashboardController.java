package com.education.platform.controller;

import com.education.platform.model.entity.Request;
import com.education.platform.model.response.ApiResponse;
import com.education.platform.security.entity.User;
import com.education.platform.service.RequestService;
import com.education.platform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/engineer")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ENGINEER')")
public class EngineerDashboardController {

    private final RequestService requestService;
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> getProfile(@AuthenticationPrincipal User engineer) {
        // Return the authenticated engineer's profile
        return ResponseEntity.ok(new ApiResponse(true, "Profile retrieved successfully", engineer));
    }

    @GetMapping("/requests")
    public ResponseEntity<ApiResponse> getRequests(@AuthenticationPrincipal User engineer) {
        List<Request> requests = requestService.getEngineerRequests(engineer.getId());
        return ResponseEntity.ok(new ApiResponse(true, "Requests retrieved successfully", requests));
    }

    @PostMapping("/requests/{requestId}/accept")
    public ResponseEntity<ApiResponse> acceptRequest(@PathVariable Long requestId) {
        Request request = requestService.updateRequestStatus(requestId, Request.RequestStatus.IN_PROGRESS);
        return ResponseEntity.ok(new ApiResponse(true, "Request accepted successfully", request));
    }

    @PostMapping("/requests/{requestId}/complete")
    public ResponseEntity<ApiResponse> completeRequest(@PathVariable Long requestId) {
        Request request = requestService.updateRequestStatus(requestId, Request.RequestStatus.COMPLETED);
        return ResponseEntity.ok(new ApiResponse(true, "Request completed successfully", request));
    }

    @PostMapping("/requests/{requestId}/cancel")
    public ResponseEntity<ApiResponse> cancelRequest(@PathVariable Long requestId) {
        Request request = requestService.updateRequestStatus(requestId, Request.RequestStatus.CANCELLED);
        return ResponseEntity.ok(new ApiResponse(true, "Request cancelled successfully", request));
    }

    @GetMapping("/schedule")
    public ResponseEntity<ApiResponse> getSchedule(@AuthenticationPrincipal User engineer) {
        Map<String, Object> schedule = requestService.getEngineerSchedule(engineer.getId());
        return ResponseEntity.ok(new ApiResponse(true, "Schedule retrieved successfully", schedule));
    }

    @PostMapping("/schedule")
    public ResponseEntity<ApiResponse> updateSchedule(
            @AuthenticationPrincipal User engineer,
            @RequestBody Map<String, Object> scheduleData) {
        Map<String, Object> updatedSchedule = requestService.updateEngineerSchedule(engineer.getId(), scheduleData);
        return ResponseEntity.ok(new ApiResponse(true, "Schedule updated successfully", updatedSchedule));
    }

    @GetMapping("/earnings")
    public ResponseEntity<ApiResponse> getEarnings(@AuthenticationPrincipal User engineer) {
        Map<String, Object> earnings = requestService.getEngineerEarnings(engineer.getId());
        return ResponseEntity.ok(new ApiResponse(true, "Earnings retrieved successfully", earnings));
    }
} 