package com.education.platform.repository;

import com.education.platform.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByEnrolledStudentsId(Long studentId);
    List<Course> findByEngineerId(Long engineerId);
} 