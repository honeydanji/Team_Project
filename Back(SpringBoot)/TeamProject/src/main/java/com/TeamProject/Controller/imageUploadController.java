package com.TeamProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.TeamProject.Service.imageUploadService;

@RestController
public class imageUploadController {

    @Autowired
    imageUploadService imageuploadservice;
    

    @PostMapping("/upload")
    public ResponseEntity<String> uploadController(@RequestParam(name = "imageFile", required = false) MultipartFile imageFile) {
        imageuploadservice.uploadService(imageFile);
        return ResponseEntity.ok("성공?");
    }

}
