package com.TeamProject.Controller.Interface;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.TeamProject.Dto.MembersDTO;

public interface MemberInterface {

    public ResponseEntity<String> registerController(@RequestBody MembersDTO memberdto);   
}
