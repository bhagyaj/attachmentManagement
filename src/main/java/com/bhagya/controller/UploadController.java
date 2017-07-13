package com.bhagya.controller;

import com.bhagya.model.Data;
import com.bhagya.service.DataService;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

//    folder to save
    private static String UPLOADED_FOLDER = "C://temp//";
//    @GetMapping("/")
//    public String index() {
//        return "upload";
//    }
//    @PostMapping("/upload") // //new annotation since 4.3
//    public String singleFileUpload(@RequestParam("file") MultipartFile file,
//                                   RedirectAttributes redirectAttributes) {
//
//        if (file.isEmpty()) {
//            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
//            return "redirect:uploadStatus";
//        }
//
//        try {
//
//            // Get the file and save it somewhere
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//            Files.write(path, bytes);
//
//            redirectAttributes.addFlashAttribute("message",
//                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return "redirect:/uploadStatus";
//    }

//    @GetMapping("/uploadStatus")
//    public String uploadStatus() {
//        return "uploadStatus";
//    }


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

}
