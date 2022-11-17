package com.sagor.product_restapi.repository;
import com.sagor.product_restapi.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByIdAndIsActiveTrue(Long id);

    List<Course> findByAllAndIsActiveTrue();
}
