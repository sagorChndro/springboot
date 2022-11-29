package com.sagor.springcrudrestapiemailsecur.controller;


import com.sagor.springcrudrestapiemailsecur.annotations.ApiController;
import com.sagor.springcrudrestapiemailsecur.model.Employee;
import com.sagor.springcrudrestapiemailsecur.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@ApiController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public Employee create(@RequestBody Employee employee){
        return employeeService.create(employee);
    }
    @PutMapping("/update")
    public Employee update(@RequestBody Employee employee){
        return employeeService.update(employee);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        employeeService.delete(id);
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable("id") Long id){
        return employeeService.get(id);
    }
    @GetMapping("/all")
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

}
