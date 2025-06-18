package com.education.platform.service.implementation;

import com.education.platform.dto.CourseDTO;
import com.education.platform.entity.Course;
import com.education.platform.exception.ResourceNotFoundException;
import com.education.platform.repository.CourseRepository;
import com.education.platform.security.entity.User;
import com.education.platform.service.CourseService;
import com.education.platform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserService userService;

    @Override
    @Transactional
    public Course createCourse(CourseDTO courseDTO) {
        User engineer = userService.getUserById(courseDTO.getEngineerId())
                .orElseThrow(() -> new ResourceNotFoundException("Engineer not found"));

        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());
        course.setEngineer(engineer);

        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void enrollStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        User student = userService.getUserById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        course.getEnrolledStudents().add(student);
        courseRepository.save(course);
    }

    @Override
    public List<Course> getStudentCourses(Long studentId) {
        return courseRepository.findByEnrolledStudentsId(studentId);
    }

    @Override
    public List<Course> getEngineerCourses(Long engineerId) {
        return courseRepository.findByEngineerId(engineerId);
    }
} 