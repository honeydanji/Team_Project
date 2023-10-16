package com.TeamProject.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    // Logger 인스턴스 생성
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    // 회원가입
    @Override
    @PostMapping("/register")
    public ResponseEntity<String> registerController(@Valid @RequestBody MembersDTO memberdto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //유효성 검사 오류가 발생한 경우
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            String errorMessage = "유효성 검사 오류 발생: " + fieldErrors.get(0).getDefaultMessage();

            // 로그로 오류 메시지 기록
            logger.error(errorMessage);

            return ResponseEntity.badRequest().body(errorMessage);
        }
        return memberservice.registerService(memberdto);
    }
}
