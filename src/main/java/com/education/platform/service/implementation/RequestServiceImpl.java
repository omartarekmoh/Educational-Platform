package com.education.platform.service.implementation;

import com.education.platform.model.entity.Request;
import com.education.platform.repository.RequestRepository;
import com.education.platform.security.entity.User;
import com.education.platform.service.RequestService;
import com.education.platform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserService userService;

    @Override
    public Request createRequest(Request request) {
        request.setStatus(Request.RequestStatus.PENDING);
        return requestRepository.save(request);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Request> getRequestById(Long id) {
        return requestRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> getRequestsByStudentId(Long studentId) {
        return requestRepository.findByStudentId(studentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> getRequestsByEngineerId(Long engineerId) {
        return requestRepository.findByEngineerId(engineerId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> getRequestsByStatus(Request.RequestStatus status) {
        return requestRepository.findByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> getRequestsByTypeAndStatus(Request.RequestType type, Request.RequestStatus status) {
        return requestRepository.findByTypeAndStatus(type, status);
    }

    @Override
    public Request updateRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public Request assignEngineer(Long requestId, Long engineerId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        
        User engineer = userService.getUserById(engineerId)
                .orElseThrow(() -> new RuntimeException("Engineer not found"));
        
        if (engineer.getUserType() != User.UserType.ENGINEER) {
            throw new RuntimeException("User is not an engineer");
        }
        
        request.setEngineer(engineer);
        request.setStatus(Request.RequestStatus.ASSIGNED);
        return requestRepository.save(request);
    }

    @Override
    public Request updateRequestStatus(Long requestId, Request.RequestStatus status) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        
        request.setStatus(status);
        return requestRepository.save(request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> getPendingRequests() {
        return requestRepository.findByStatus(Request.RequestStatus.PENDING);
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalRequests() {
        return requestRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> getEngineerRequests(Long engineerId) {
        return requestRepository.findByEngineerId(engineerId);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getEngineerSchedule(Long engineerId) {
        // TODO: Implement logic to fetch engineer schedule
        return Map.of("message", "Schedule not implemented yet");
    }

    @Override
    public Map<String, Object> updateEngineerSchedule(Long engineerId, Map<String, Object> scheduleData) {
        // TODO: Implement logic to update engineer schedule
        return Map.of("message", "Schedule update not implemented yet");
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getEngineerEarnings(Long engineerId) {
        // TODO: Implement logic to fetch engineer earnings
        return Map.of("message", "Earnings not implemented yet");
    }
} 