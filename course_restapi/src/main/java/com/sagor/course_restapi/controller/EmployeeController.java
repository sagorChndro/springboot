package com.sagor.product_restapi.controller;

import com.sagor.product_restapi.model.Employee;
import com.sagor.product_restapi.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"GET", "PUT", "DELETE", "POST"})
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public Employee save(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable("id") Long id){
        return employeeService.get(id);
    }

    @PutMapping("/update")
    public Employee update(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        employeeService.delete(id);
    }

    @GetMapping("/all")
    public List<Employee> getAll(){
        return employeeService.getAll();
    }
}
