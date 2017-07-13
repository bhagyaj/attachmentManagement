package com.bhagya.controller;

import com.bhagya.model.Data;
import com.bhagya.service.DataService;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.sql.Blob;


/**
 * Created by brupasinghe on 7/13/2017.
 */
@RestController
public class UploadController {
    @Autowired
    DataService dataService;

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addData(@RequestPart("note") String body,
                                        @RequestPart("location") String location,
                                        @RequestPart(value = "file", required = false) MultipartFile multipartFile){
        System.out.println("method call");
        Data data = new Data();
        data.setBody(body);
        data.setLocation(location);
        try {
            Blob file = Hibernate.getLobCreator(sessionFactory.getCurrentSession()).createBlob(multipartFile.getInputStream(), multipartFile.getSize());
            data.setFile(file);
        }catch (HibernateException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataService.save(data);
    }

       @RequestMapping(value="/Data/{userId}", method= RequestMethod.GET, produces = "application/pdf")
    @CrossOrigin
    public Data get(@PathVariable("userId") int id){
        System.out.println(id);
        return dataService.get(id);
    }


//    @RequestMapping(value = "/Data/{userId}", method = RequestMethod.GET, produces = "application/pdf")
//    public ResponseEntity<InputStreamResource> downloadPDFFile()
//            throws IOException {
//
//        ClassPathResource pdfFile = new ClassPathResource("pdf-sample.pdf");
//
//        return ResponseEntity
//                .ok()
//                .contentLength(pdfFile.contentLength())
//                .contentType(
//                        MediaType.parseMediaType("application/octet-stream"))
//                .body(new InputStreamResource(pdfFile.getInputStream()));
//    }
}
