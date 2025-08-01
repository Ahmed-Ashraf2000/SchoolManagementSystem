package com.spring.school.service;

import com.spring.school.model.Course;
import com.spring.school.model.User;
import com.spring.school.repository.CourseRepository;
import com.spring.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional
    public void addNewCourse(Course course) {
        courseRepository.save(course);
    }

    public Course getCourseById(String courseId) {
        return courseRepository.getCoursesByCourseId(Long.valueOf(
                courseId)).orElseThrow(
                () -> new RuntimeException("No course found with this ID " + courseId));
    }

    @Transactional
    public void addStudentToCourse(String email, Long courseId) {
        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found with email: " + email));

        Course course = courseRepository.findById(Math.toIntExact(courseId))
                .orElseThrow(
                        () -> new RuntimeException("Course not found with id: " + courseId));

        student.getCourses().add(course);

        if (student.getEmail() != null) {
            student.setConfirmEmail(student.getEmail());
        }
        if (student.getPassword() != null) {
            student.setConfirmPassword(student.getPassword());
        }

        userRepository.save(student);
    }

    @Transactional
    public void removeStudentFromCourse(Long studentId, Long courseId) {
        Course course = courseRepository.findById(Math.toIntExact(courseId))
                .orElseThrow(
                        () -> new RuntimeException("Course not found with id: " + courseId));

        User student = userRepository.findByUserId(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        student.getCourses().remove(course);

        if (student.getEmail() != null) {
            student.setConfirmEmail(student.getEmail());
        }
        if (student.getPassword() != null) {
            student.setConfirmPassword(student.getPassword());
        }

        userRepository.save(student);
    }
}
