package com.example.mongocrud.repository;

import com.example.mongocrud.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findByEmpId(String empId);

    Optional<Employee> deleteByEmpId(String empId);
}
