package com.education.platform.service;

import com.education.platform.dto.CourseDTO;
import com.education.platform.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course createCourse(CourseDTO courseDTO);
    Optional<Course> getCourseById(Long id);
    List<Course> getAllCourses();
    Course updateCourse(Course course);
    void deleteCourse(Long id);
    void enrollStudent(Long courseId, Long studentId);
    List<Course> getStudentCourses(Long studentId);
    List<Course> getEngineerCourses(Long engineerId);
} 