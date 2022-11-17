package com.sagor.course_restapi.repository;


import com.sagor.course_restapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
