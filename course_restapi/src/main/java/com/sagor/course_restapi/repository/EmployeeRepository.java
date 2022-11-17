package com.sagor.product_restapi.repository;

import com.sagor.product_restapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
