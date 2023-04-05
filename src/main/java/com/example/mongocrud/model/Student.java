package com.example.mongocrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String stuId;
    private String name;
    private String email;
    private String branch;

    public Document toDocument(){
        Document doc = new Document();
        doc.put("stuId",this.stuId);
        doc.put("name",this.name);
        doc.put("email",this.email);
        doc.put("branch",this.branch);
        return doc;
    }
}
