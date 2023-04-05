package com.example.mongocrud.repository;

import com.example.mongocrud.model.Employee;

import java.util.List;

public interface CustomRepository {

    List<Employee> findByText(String text);
}
