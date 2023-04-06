package com.example.mongocrud.service;

import com.example.mongocrud.model.Employee;
import com.mongodb.client.result.UpdateResult;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();

    Employee getEmployeeById(String empId);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Employee employee, String empId);

    void deleteEmployee(String empId);

    List<Employee> findEmployee();

    //    UpdateResult updateEmployeeTemplate(Employee employee, String empId);
}
