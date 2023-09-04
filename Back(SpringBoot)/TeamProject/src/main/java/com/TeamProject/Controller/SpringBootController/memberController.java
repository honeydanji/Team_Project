package com.TeamProject.Controller.SpringBootController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.TeamProject.Dto.membersDTO;
import com.TeamProject.Service.SpringBootService.memberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class memberController {

    private final memberService memberservice;

    @PostMapping("/register")
    public ResponseEntity<String> registerController(@RequestBody membersDTO memberdto) {
        return memberservice.registerService(memberdto);
    }
    
}
