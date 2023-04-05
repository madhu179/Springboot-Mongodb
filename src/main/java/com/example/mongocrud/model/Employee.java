package com.example.mongocrud.model;

import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employee")
public class Employee {
//    @Id
//    @Indexed
    private String empId;
    private String name;
    private String email;
    private String designation;

//    public Document toDoc(){
//        Document doc = new Document();
//    }
}
