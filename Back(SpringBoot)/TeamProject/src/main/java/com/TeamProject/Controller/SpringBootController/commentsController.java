package com.TeamProject.Controller.SpringBootController;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TeamProject.Service.SpringBootService.commentsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class commentsController {

    private final commentsService commentsservice;

    @PostMapping("/commentUpload/{historyId}")
    public ResponseEntity<String> commentUpload(@PathVariable int historyId, 
                                                @RequestParam String comment, 
                                                Authentication authentication) {
        return commentsservice.commentUpload(historyId, comment, authentication);
    }
    
}
