package com.TeamProject.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TeamProject.Controller.Interface.CommentsInterface;
import com.TeamProject.Service.SpringBootService.CommentsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentsController implements CommentsInterface {

    private final CommentsService commentsservice;

    @Override
    @PostMapping("/commentUpload/{historyId}")
    public ResponseEntity<String> commentUpload(@PathVariable int historyId, // 히스토리 몇번?
                                                @RequestParam String contents,// 기록할 내용
                                                Authentication authentication) {// 권한
                                                    
        return commentsservice.commentUpload(historyId, contents, authentication);
    }
    
    @Override
    @PutMapping("/commentUpdate/{historyId}")
    public ResponseEntity<String> commentUpdate(@PathVariable int historyId,    // 히스토리 몇번?
                                                @RequestParam String contents,   // 수정할 내용
                                                Authentication authentication) {// 권한
        return commentsservice.commentUpdate(historyId, contents, authentication);
    }

    @Override
    @DeleteMapping("/commentDelete/{historyId}")
    public ResponseEntity<String> commentDelete(@PathVariable int historyId,    // 히스토리 몇번?
                                                Authentication authentication) {
        return commentsservice.commentDelete(historyId, authentication);
    }

}
