package com.TeamProject.Controller.Interface;

import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadInterface {

    ResponseEntity<Object> uploadController(
        @RequestParam(name = "pngFile", required = false) MultipartFile pngFile,
        @RequestParam(name = "plyFile", required = false) MultipartFile plyFile,
        Authentication authentication
    );

    ResponseEntity<Resource> getImage(@PathVariable String imageName) throws MalformedURLException;
}
