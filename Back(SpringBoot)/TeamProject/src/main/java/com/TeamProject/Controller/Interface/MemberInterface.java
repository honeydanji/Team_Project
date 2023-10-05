package com.TeamProject.Controller.Interface;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import com.TeamProject.Dto.MembersDTO;

public interface MemberInterface {

    public ResponseEntity<String> registerController(@RequestBody MembersDTO memberdto, BindingResult bindingResult);   
    // 유효성 검사 변수 추가
}
