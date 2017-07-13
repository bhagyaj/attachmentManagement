package com.bhagya.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by brupasinghe on 7/13/2017.
 */
@Entity
@Table(name = "DATA")
public class Data {
    @Id
    @GeneratedValue
    @Column(name = "userId")
    private int id;
    @Column(name = "body")
    private String body;
    @Column(name = "location")
    private String location;
    @Column(name = "file")
    @Lob
    private Blob file;

//    getters

    public int getId() {
        return id;
    }

    public Blob getFile() {
        return file;
    }

    public String getBody() {
        return body;
    }

    public String getLocation() {
        return location;
    }
    //    setters


    public void setBody(String body) {
        this.body = body;
    }

    public void setFile(Blob file) {
        this.file = file;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
