package com.sagor.springcrudrestapiemailsecur.service.impl;

import com.sagor.springcrudrestapiemailsecur.model.Employee;
import com.sagor.springcrudrestapiemailsecur.repository.EmployeeRepository;
import com.sagor.springcrudrestapiemailsecur.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    // repository er maddhome joto dhoroner database operation ache segula korbo
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        // delete korte gele prothomoto amader repository theke id ta ke get korte hobe
        Employee employee = employeeRepository.findById(id).get();
        if(employee == null){
            return;
        }
        employeeRepository.delete(employee);

    }

    @Override
    public Employee get(Long id) {
        // get korte gele prothomoto amader repository theke id ta ke get korte hobe
        Employee employee = employeeRepository.findById(id).get();
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        // get all korte gele repository theke findAll() method use korte hobe
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }
}
