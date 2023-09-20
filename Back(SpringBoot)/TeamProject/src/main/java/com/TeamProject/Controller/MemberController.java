package com.TeamProject.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.TeamProject.Controller.Interface.MemberInterface;
import com.TeamProject.Dto.MembersDTO;
import com.TeamProject.Service.SpringBootService.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController implements MemberInterface{

    private final MemberService memberservice;

    // 회원가입
    @Override
    @PostMapping("/register")
    public ResponseEntity<String> registerController(@RequestBody MembersDTO memberdto) {
        return memberservice.registerService(memberdto);
    }
}
