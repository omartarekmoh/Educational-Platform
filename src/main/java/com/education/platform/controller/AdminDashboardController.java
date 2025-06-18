package com.education.platform.controller;

import com.education.platform.model.entity.Request;
import com.education.platform.model.response.ApiResponse;
import com.education.platform.security.entity.User;
import com.education.platform.service.RequestService;
import com.education.platform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminDashboardController {

    private final UserService userService;
    private final RequestService requestService;

    @GetMapping("/users")
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse(true, "Users retrieved successfully", users));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(user -> ResponseEntity.ok(new ApiResponse(true, "User retrieved successfully", user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/users/{userId}/approve")
    public ResponseEntity<ApiResponse> approveUser(@PathVariable Long userId) {
        User user = userService.approveUser(userId);
        return ResponseEntity.ok(new ApiResponse(true, "User approved successfully", user));
    }

    @PostMapping("/users/{userId}/suspend")
    public ResponseEntity<ApiResponse> suspendUser(@PathVariable Long userId) {
        User user = userService.suspendUser(userId);
        return ResponseEntity.ok(new ApiResponse(true, "User suspended successfully", user));
    }

    @GetMapping("/requests")
    public ResponseEntity<ApiResponse> getAllRequests() {
        List<Request> requests = requestService.getAllRequests();
        return ResponseEntity.ok(new ApiResponse(true, "Requests retrieved successfully", requests));
    }

    @PostMapping("/requests/{requestId}/assign/{engineerId}")
    public ResponseEntity<ApiResponse> assignEngineer(
            @PathVariable Long requestId,
            @PathVariable Long engineerId) {
        Request request = requestService.assignEngineer(requestId, engineerId);
        return ResponseEntity.ok(new ApiResponse(true, "Engineer assigned successfully", request));
    }

    @GetMapping("/engineers")
    public ResponseEntity<ApiResponse> getAllEngineers() {
        return ResponseEntity.ok(new ApiResponse(true, "Engineers retrieved successfully",
                userService.getUsersByType(User.UserType.ENGINEER)));
    }

    @GetMapping("/students")
    public ResponseEntity<ApiResponse> getAllStudents() {
        return ResponseEntity.ok(new ApiResponse(true, "Students retrieved successfully",
                userService.getUsersByType(User.UserType.STUDENT)));
    }

    @GetMapping("/dashboard/stats")
    public ResponseEntity<ApiResponse> getDashboardStats() {
        var stats = Map.of(
            "totalUsers", userService.getTotalUsers(),
            "totalEngineers", userService.getUsersByType(User.UserType.ENGINEER).size(),
            "totalStudents", userService.getUsersByType(User.UserType.STUDENT).size(),
            "totalRequests", requestService.getTotalRequests(),
            "pendingRequests", requestService.getPendingRequests().size()
        );
        return ResponseEntity.ok(new ApiResponse(true, "Dashboard stats retrieved successfully", stats));
    }
} 