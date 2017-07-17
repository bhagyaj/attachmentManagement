package com.bhagya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Blob;

/**
 * Created by brupasinghe on 7/14/2017.
 */
@Entity
@Table(name = "DataWrapper")
public class DataWrapper{
    @Column(name = "body")
    private String body;
    @Column(name = "location")
    private String location;
    @Id
    @Column(name = "fileName")
    private String fileName;
    @Column(name = "file")
    private Blob file;
//    public DataWrapper(String body, String location, String fileName, Blob file){
//        this.body = body;
//        this.location = location;
//        this.fileName = fileName;
//        this.file = file;
//    }

//    setters

    public void setFile(Blob file) {
        this.file = file;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // getters
    public String getBody() {
        return body;
    }

    public String getFileName() {
        return fileName;
    }

    public String getLocation() {
        return location;
    }

    public Blob getFile() {
        return file;
    }

}
