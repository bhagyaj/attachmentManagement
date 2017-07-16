package com.bhagya.controller;

import com.bhagya.model.Data;
import com.bhagya.model.DataWrapper;
import com.bhagya.service.DataService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.misc.IOUtils;
import sun.misc.Resource;

import javax.activation.FileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by brupasinghe on 7/13/2017.
 */
@RestController
public class UploadController {
    @Autowired
    DataService dataService;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    ServletContext servletContext;

    @Autowired
    FileDownloadController fileDownloadController;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addData(@RequestPart("note") String body,
                                        @RequestPart("location") String location,
                                        @RequestPart(value = "file", required = false) MultipartFile multipartFile){
        System.out.println("method call");
        Data data = new Data();
        data.setBody(body);
        data.setLocation(location);
        data.setFileName(multipartFile.getOriginalFilename());
        try {
            Blob file = Hibernate.getLobCreator(sessionFactory.getCurrentSession()).createBlob(multipartFile.getInputStream(), multipartFile.getSize());
//            data.setFile(file);
            DataWrapper dataWrapper= new DataWrapper(body,location,multipartFile.getOriginalFilename(),file);
            dataService.saveFile(dataWrapper);
        }catch (HibernateException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        dataService.save(data);

    }

    //working get



//last try

//    @RequestMapping(value="/Data/{userId}", method= RequestMethod.GET)
//    @CrossOrigin
//    @JsonIgnore
//    public ResponseEntity<List<byte[]>> get(@PathVariable("userId") int id) throws SQLException {
//        List<byte[]> myOutput= null;
//        Data data= dataService.get(id);
//        Blob file =  data.getFile();
//        int blobLength = (int) file.length();
//        String note= data.getBody();
//        String location = data.getLocation();
//        myOutput.add(file.getBytes(1,blobLength));
//        myOutput.add(note.getBytes());
//        myOutput.add(location.getBytes());
//
//        System.out.println(id);
//        return ResponseEntity.ok(myOutput);
//    }


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



//    @RequestMapping(value="/Data/{userId}", method= RequestMethod.GET)
//    @CrossOrigin
//    @JsonIgnore
//    public ResponseEntity<Data> get(@PathVariable("userId") int id) throws SQLException {
//
//        return ResponseEntity.ok(dataService.get(id));
//    }

    @RequestMapping("/Data/{userId}")
    public Data getData(@PathVariable("userId") int id) throws IOException, SQLException {


        String fileName = dataService.get(id).getFileName();
        fileDownloadController.getProfilePic(fileName);
        return dataService.get(id);
//        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(file))).body(Files.readAllBytes(file.toPath()));
    }

}
