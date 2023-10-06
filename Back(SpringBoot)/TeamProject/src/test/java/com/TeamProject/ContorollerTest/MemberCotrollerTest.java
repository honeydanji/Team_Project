package com.TeamProject.ContorollerTest;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
        memberDTO.setName("test");
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

    @Test
    @DisplayName("이름 유효성 검사하기")
    @WithMockUser()
    public void testInvalidMembersDTO() throws Exception {
        // 가짜 MemberDTO 객체 생성
        MembersDTO membersDTO = new MembersDTO();
        membersDTO.setName(null); // 이름 필드가 null이므로 유효성 검사 실패
        
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
            .with(csrf()) // 403 에러 방지해결
            .contentType(MediaType.APPLICATION_JSON) // JSON 형식으로 요청
            .content(asJsonString(membersDTO))) // 객체를 JSON 문자열로 변환하여 전달
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.content().string("이름을 입력해 주세요"));
    }

    // 403 에러(권한문제) > with(csrf()) 추가 > 401 발생
    // 401 에러 > withMockUser() 추가 > 400 발생
    // 400 에러(잘못된 요청) > 실제 컨트롤러 클래스에 유효성 검사 로직을 추가 > 해결 x
    // 에러 해결 : 유효성 검사에 실패하면 BadRequest로 처리해야하는데 ok로 처리 했기 때문이었음. 
    
    // 오류 : 오류메시지가 계속 비어 있음.
    // 원인 : 실제 Controller에서 요청본문을 Json형태로 받아야 하는데 그러지 않았음. 
    //        (@RequestBody 는 요청 분문을 Json형태로 받아야함.)
    // 해결 : MemberDTO객체를 Json 문자열로 변환후에 전송함. 

    
    
    
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

