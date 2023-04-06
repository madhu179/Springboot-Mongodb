package com.example.mongocrud.service.impl;

import com.example.mongocrud.model.Employee;
import com.example.mongocrud.repository.CustomRepository;
import com.example.mongocrud.repository.EmployeeRepository;
import com.example.mongocrud.service.EmployeeService;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomRepository repo;

    @Autowired
    private MongoTemplate template;

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
        Optional<Employee> op = employeeRepository.findByEmpId(employee.getEmpId());
        if(op.isPresent()) {
            throw new RuntimeException("Employee with id : "+employee.getEmpId()+" already exists");
        }
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

    @Override
    public List<Employee> findEmployee(){
        return repo.findByText("random");
    }

    //    @Override
//    public UpdateResult updateEmployeeTemplate(Employee employee, String empId){
//        Query query= new Query();
//        query.addCriteria(Criteria.where("empId").is(empId));
//
//        Update update = new Update();
//        update.set("name", employee.getName());
//        update.set("email", employee.getEmail());
//
//        return template.upsert(query, update, Employee.class);
//    }

}
