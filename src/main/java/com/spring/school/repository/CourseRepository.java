package com.spring.school.repository;

import com.spring.school.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByOrderByCourseName();

    Optional<Course> getCoursesByCourseId(Long courseId);
}
