package com.example.mongocrud.repository;

import com.example.mongocrud.model.Student;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Component
@Repository
public class CustomStudentRepository {

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

//    @Override
    public Student findById(String stuId) {

        final List<Student> students = new ArrayList<>();

        MongoDatabase database = client.getDatabase("testdb");
        MongoCollection<Document> collection = database.getCollection("student");

        FindIterable<Document> result = collection.find(new Document("stuId", stuId));

        result.forEach(doc ->
            students.add(converter.read(Student.class,doc)));

        if(students.size()==0)
            throw new RuntimeException("No student exists");

        return students.get(0);
    }

    public List<Student> findAll() {

        final List<Student> students = new ArrayList<>();
        MongoDatabase database = client.getDatabase("testdb");
        MongoCollection<Document> collection = database.getCollection("student");

        FindIterable<Document> result = collection.find();

        result.forEach(doc ->
                students.add(converter.read(Student.class,doc)));

        if(students.size()==0)
            throw new RuntimeException("No student exists");

        return students;
    }

    public Student addStudent(Student student) {
        MongoDatabase database = client.getDatabase("testdb");
        MongoCollection<Document> collection = database.getCollection("student");

        collection.insertOne(student.toDocument());

        return student;

    }

    public Student updateStudent(Student student, String stuId) {
        MongoDatabase database = client.getDatabase("testdb");
        MongoCollection<Document> collection = database.getCollection("student");

        collection.updateOne(new Document("stuId", stuId),
                new Document("$set", student.toDocument()));

        return student;

    }

    public Student deleteStudent(String stuId) {
        MongoDatabase database = client.getDatabase("testdb");
        MongoCollection<Document> collection = database.getCollection("student");

        Document doc = collection.findOneAndDelete(new Document("stuId", stuId));

        return converter.read(Student.class, doc);

    }


}
