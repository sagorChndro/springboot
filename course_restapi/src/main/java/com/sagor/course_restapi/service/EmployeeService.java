package com.sagor.course_restapi.service;



import com.sagor.course_restapi.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);
    Employee update(Employee employee);
    Employee get(Long id);
    void delete(Long id);
    List<Employee> getAll();
}
