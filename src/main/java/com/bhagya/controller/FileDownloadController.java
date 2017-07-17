package com.bhagya.controller;

import com.bhagya.service.DataService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.FileTypeMap;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by bhagya on 7/17/17.
 */
@RestController
public class FileDownloadController {
    @Autowired
    DataService dataService;
    @RequestMapping("/DataWrapper/{fileName}")
    public ResponseEntity<byte[]> getProfilePic(@PathVariable("fileName") String fileName) throws IOException, SQLException {


        Blob blob = dataService.getFile(fileName).getFile();

        int blobLength = (int) blob.length();
        byte[] blobAsBytes = blob.getBytes(1, blobLength);
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(blob.toString()))).body(blob.getBytes(1,blobLength));
//        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(file))).body(Files.readAllBytes(file.toPath()));
    }

}
