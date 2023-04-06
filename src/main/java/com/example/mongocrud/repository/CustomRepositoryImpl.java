package com.example.mongocrud.repository;

import com.example.mongocrud.model.Employee;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomRepositoryImpl implements CustomRepository {
    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Employee> findByText(String text) {

        final List<Employee> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("testdb");
        MongoCollection<Document> collection = database.getCollection("employee");


        FindIterable<Document> result = collection.find(new Document("empId", "007"));

        result.forEach(doc -> posts.add(converter.read(Employee.class,doc)));

        return posts;
    }
}
