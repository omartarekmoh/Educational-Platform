package com.education.platform.service;

import com.education.platform.model.entity.Request;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RequestService {
    Request createRequest(Request request);
    Optional<Request> getRequestById(Long id);
    List<Request> getRequestsByStudentId(Long studentId);
    List<Request> getRequestsByEngineerId(Long engineerId);
    List<Request> getRequestsByStatus(Request.RequestStatus status);
    List<Request> getRequestsByTypeAndStatus(Request.RequestType type, Request.RequestStatus status);
    Request updateRequest(Request request);
    void deleteRequest(Long id);
    Request assignEngineer(Long requestId, Long engineerId);
    Request updateRequestStatus(Long requestId, Request.RequestStatus status);
    List<Request> getAllRequests();
    long getTotalRequests();
    List<Request> getPendingRequests();
    List<Request> getEngineerRequests(Long engineerId);
    Map<String, Object> getEngineerSchedule(Long engineerId);
    Map<String, Object> updateEngineerSchedule(Long engineerId, Map<String, Object> scheduleData);
    Map<String, Object> getEngineerEarnings(Long engineerId);
} 