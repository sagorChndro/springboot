package com.sagor.product_restapi.service;

import com.sagor.product_restapi.model.Address;
import com.sagor.product_restapi.model.Employee;
import com.sagor.product_restapi.repository.EmployeeRepository;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DbInit {
    private final EmployeeRepository employeeRepository;

    public DbInit(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    void init(){
        Employee employee = new Employee();
        employee.setName("Nidhon Mondol");
        employee.setName("Tutul Das");
        Address address = new Address();
        address.setCityName("Ashulia Tongabari");
        address.setCountryName("Bangladesh");
        address.setCityName("Mirpur12");
        address.setCountryName("Bangladesh");
        employeeRepository.save(employee);
    }
}
