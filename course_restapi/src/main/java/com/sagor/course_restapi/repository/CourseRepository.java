package com.sagor.course_restapi.repository;
import com.sagor.course_restapi.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByIdAndIsActiveTrue(Long id);

    List<Course> findByAllAndIsActiveTrue();
}
