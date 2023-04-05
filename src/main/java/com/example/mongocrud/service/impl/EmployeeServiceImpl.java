package com.example.mongocrud.service.impl;

import com.example.mongocrud.model.Employee;
import com.example.mongocrud.repository.EmployeeRepository;
import com.example.mongocrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(String empId) {
        Optional<Employee> op = employeeRepository.findByEmpId(empId);
        if(op.isPresent()){
            return op.get();
        } else{
            throw new RuntimeException("Employee with id : "+empId+" not present");
        }
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee, String empId) {
        Optional<Employee> op = employeeRepository.findByEmpId(empId);
        if(op.isPresent()){
            Employee emp = op.get();
            emp.setName(employee.getName());
            emp.setEmail(employee.getEmail());
            emp.setDesignation(employee.getDesignation());
            employeeRepository.save(emp);
            return emp;
        } else{
            throw new RuntimeException("Employee with id : "+empId+" not present");
        }
    }

    @Override
    public void deleteEmployee(String empId) {
        Optional<Employee> op = employeeRepository.deleteByEmpId(empId);
        if(!op.isPresent()){
            throw new RuntimeException("Employee with id : "+empId+" not present");
        }
    }

}
