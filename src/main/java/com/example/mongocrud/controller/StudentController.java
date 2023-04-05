package com.example.mongocrud.controller;

import com.example.mongocrud.model.Student;
import com.example.mongocrud.repository.CustomStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private CustomStudentRepository repo;

    @GetMapping("/")
    public List<Student> getStudents(){
        return repo.findAll();
    }

    @GetMapping("/{stuId}")
    public Student getStudentById(@PathVariable String stuId){
        return repo.findById(stuId);
    }

    @PostMapping("/")
    public Student addStudent(@RequestBody Student student){
        return repo.addStudent(student);
    }

    @PutMapping("/{stuId}")
    public Student updateStudent(@RequestBody Student student, @PathVariable String stuId){
        return repo.updateStudent(student, stuId);
    }

    @DeleteMapping("/{stuId}")
    public Student deleteStudent(@PathVariable String stuId){
        return repo.deleteStudent(stuId);
    }
}
