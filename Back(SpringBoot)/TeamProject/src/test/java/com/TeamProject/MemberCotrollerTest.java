package com.TeamProject;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.TeamProject.Controller.MemberController;
import com.TeamProject.Dto.MembersDTO;
import com.TeamProject.Service.SpringBootService.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MemberController.class)
public class MemberCotrollerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @Test
    @DisplayName("회원 등록 테스트")
    @WithMockUser
    void memberTest() throws Exception {
        // 요청 본문에 사용할 MembersDTO 객체 생성
        MembersDTO memberDTO = new MembersDTO();
        memberDTO.setName("Test");
        memberDTO.setLoginEmail("Test3680@test.com");
        memberDTO.setPassword("1234");
        memberDTO.setConfirmPassword("1234");
        memberDTO.setPhoneNumber("1234567890");
        memberDTO.setCompanyName("Test");
        
        // memberService.registerService 메서드 호출 시의 동작을 가짜(mock)로 설정
        when(memberService.registerService(memberDTO)).thenReturn(ResponseEntity.ok("회원 등록 성공"));
        
        // /register 엔드포인트로 POST 요청을 보내고, MembersDTO 객체를 JSON 형식으로 전송
        mockMvc.perform(
                post("/register")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(memberDTO)))
                .andExpect(status().isOk());
    }

    // 객체를 JSON 문자열로 변환하기 위한 도우미 메서드
    private String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
