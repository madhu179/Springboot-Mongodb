package com.example.mongocrud.controller;

import com.example.mongocrud.model.Employee;
import com.example.mongocrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee/")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("/{empId}")
    public Employee getEmployeeById(@PathVariable String empId){
        return employeeService.getEmployeeById(empId);
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{empId}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable String empId){
        return employeeService.updateEmployee(employee, empId);
    }

    @DeleteMapping("/{empId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteEmployee(@PathVariable String empId){
        employeeService.deleteEmployee(empId);
        return "The employee with Id : "+empId+" is deleted successfully";
    }

    @GetMapping("/find")
    public List<Employee> findEmployee(){
        return employeeService.findEmployee();
    }

    //    @PutMapping("/template/{empId}")
//    @ResponseStatus(HttpStatus.OK)
//    public Employee updateEmployeeTemplate(@RequestBody Employee employee, @PathVariable String empId){
//        return employeeService.updateEmployeeTemplate(employee, empId);
//    }

}
