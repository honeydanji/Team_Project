package com.TeamProject.Service.SpringBootService;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.TeamProject.Domain.Members;
import com.TeamProject.Dto.MembersDTO;
import com.TeamProject.Repository.MembersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MembersRepository memberrepository;
    private final BCryptPasswordEncoder secret;

    public ResponseEntity<String> registerService(MembersDTO memberdto) {

        if(memberrepository.findByLoginEmail(memberdto.getLoginEmail()) == null) {
            if (!memberdto.getPassword().equals(memberdto.getConfirmPassword())) {
            return ResponseEntity.ok("비밀번호를 확인해주세요.");
        } else {
            Members member = new Members();

            member.setName(memberdto.getName());
            member.setLoginEmail(memberdto.getLoginEmail());
            member.setPassword(secret.encode(memberdto.getPassword())); // password 암호화
            member.setConfirmPassword(member.getPassword());
            member.setPhoneNumber(memberdto.getPhoneNumber());
            member.setCompanyName(memberdto.getCompanyName());
            member.setRole("ROLE_MEMBER");
            member.setCreationDate(new Date());

            memberrepository.save(member);

            return ResponseEntity.ok("회원가입 성공.");
        }
    }
    return ResponseEntity.ok("이미 존재하는 이메일입니다.");

    }
}

