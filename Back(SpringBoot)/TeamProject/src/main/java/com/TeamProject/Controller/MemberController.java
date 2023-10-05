package com.TeamProject.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.TeamProject.Controller.Interface.MemberInterface;
import com.TeamProject.Dto.MembersDTO;
import com.TeamProject.Service.SpringBootService.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController implements MemberInterface{
    
    private final MemberService memberservice;

    // 회원가입
    @Override
    @PostMapping("/register")
    public ResponseEntity<String> registerController(@Valid @RequestBody MembersDTO memberdto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //유효성 검사 오류가 발생한 경우
            List<ObjectError> errors = bindingResult.getAllErrors();
            return ResponseEntity.badRequest().body("유효성 검사 오류 발생: " + errors.get(0).getDefaultMessage());
        }
        
        return memberservice.registerService(memberdto);
    }
}
