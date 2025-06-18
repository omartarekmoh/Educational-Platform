package com.education.platform.repository;

import com.education.platform.model.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByStudentId(Long studentId);
    List<Request> findByEngineerId(Long engineerId);
    List<Request> findByStatus(Request.RequestStatus status);
    List<Request> findByTypeAndStatus(Request.RequestType type, Request.RequestStatus status);
    List<Request> findBySubjectAndStatus(String subject, Request.RequestStatus status);
} 