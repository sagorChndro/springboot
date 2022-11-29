package com.sagor.springcrudrestapiemailsecur.service;

import com.sagor.springcrudrestapiemailsecur.model.Employee;

import java.util.List;

public interface EmployeeService {
    // ei service ta serviceimplement e  implementation and controller e inject korte hobe
    Employee create(Employee employee);
    Employee update(Employee employee);
    void delete(Long id);
    Employee get(Long id);
    List<Employee> getAll();
}
