package com.TeamProject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.TeamProject.Controller.MemberController;
import com.TeamProject.Service.SpringBootService.MemberService;

@WebMvcTest(MemberController.class)
public class MemberCotrollerTest {
    
    @Autowired
    private MockMvc MockMvc;

    // 컨트롤러에서 잡고 있는 Bean 객체에 대해 Mock 형태의 객체를 생성해줌
    @MockBean
    MemberService memberservice;

    @Test
    @DisplayName("Member 데이터 가져오기 테스트")
    void getMemberTest() throws Exception {

    // given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
    }
}
