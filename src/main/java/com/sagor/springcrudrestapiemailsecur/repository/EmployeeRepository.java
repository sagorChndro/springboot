package com.sagor.springcrudrestapiemailsecur.repository;

import com.sagor.springcrudrestapiemailsecur.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
