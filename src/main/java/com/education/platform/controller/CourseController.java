package com.education.platform.controller;

import com.education.platform.dto.CourseDTO;
import com.education.platform.dto.CourseEnrollmentDTO;
import com.education.platform.dto.CourseUpdateDTO;
import com.education.platform.entity.Course;
import com.education.platform.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasRole('ENGINEER')")
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        Course course = courseService.createCourse(courseDTO);
        return ResponseEntity.ok(convertToDTO(course));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id)
                .map(course -> ResponseEntity.ok(convertToDTO(course)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ENGINEER')")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseUpdateDTO updateDTO) {
        return courseService.getCourseById(id)
                .map(course -> {
                    updateCourseFromDTO(course, updateDTO);
                    Course updatedCourse = courseService.updateCourse(course);
                    return ResponseEntity.ok(convertToDTO(updatedCourse));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ENGINEER')")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/enroll")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Void> enrollInCourse(@PathVariable Long id, @Valid @RequestBody CourseEnrollmentDTO enrollmentDTO) {
        courseService.enrollStudent(id, enrollmentDTO.getStudentId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasRole('STUDENT') and authentication.principal.id == #studentId")
    public ResponseEntity<List<CourseDTO>> getStudentCourses(@PathVariable Long studentId) {
        List<CourseDTO> courses = courseService.getStudentCourses(studentId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/engineer/{engineerId}")
    @PreAuthorize("hasRole('ENGINEER') and authentication.principal.id == #engineerId")
    public ResponseEntity<List<CourseDTO>> getEngineerCourses(@PathVariable Long engineerId) {
        List<CourseDTO> courses = courseService.getEngineerCourses(engineerId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(courses);
    }

    private CourseDTO convertToDTO(Course course) {
        return CourseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .price(course.getPrice())
                .engineerId(course.getEngineer().getId())
                .build();
    }

    private void updateCourseFromDTO(Course course, CourseUpdateDTO dto) {
        if (dto.getTitle() != null) {
            course.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            course.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            course.setPrice(dto.getPrice());
        }
    }
} 