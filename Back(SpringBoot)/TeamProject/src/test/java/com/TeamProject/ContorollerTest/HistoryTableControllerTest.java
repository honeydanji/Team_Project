package com.TeamProject.ContorollerTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.TeamProject.Controller.HistoryTableController;
import com.TeamProject.Domain.HistoryView;
import com.TeamProject.Service.SpringBootService.HistoryTableService;

@WebMvcTest(HistoryTableController.class) // 단위테스트 가능
public class HistoryTableControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryTableService historyTableService;
    // 가짜 Service 객체를 만든다.

    @Test
    @DisplayName("HitoryTabe 기본 정보") // Test 이름
    @WithMockUser(roles = "USER")
    public void testHistoryUpdateDate() throws Exception {
       
        // Mock Authentication 갹체 생성 
        Authentication authentication = Mockito.mock(Authentication.class);
        //// Mockito로 만든 Authentication 가짜 객체는 유저 정보가 없어도 된다.
        
        // historyTableService.historyUpdateDate()가 호출될 때 반환할 가짜 데이터
        Map<Object, Object> fakeHistoryData = new HashMap<>();
        fakeHistoryData.put("Test1", "Test11");
        fakeHistoryData.put("Test2", "Test22");
        
        // historyTableService.historyUpdateDate() 메서드의 동작 설정
        Mockito.when(historyTableService.historyUpdateDate(authentication)).thenReturn(fakeHistoryData);

        // GET /history 엔드포인트 호출 및 결과 검증
        mockMvc.perform(MockMvcRequestBuilders.get("/history")) // perform : 테스트를 실행한다.
                .andExpect(MockMvcResultMatchers.status().isOk()); // andExpct : 테스트 기대값
    }

    /*
     * 401 에러 및 해결
     * 원인 : Spring-Security를 사용하는데 별도의 권한을 주지 않았기 때문에 발생.
     * 해결 : @withMOckUser(role = "USER") 를 이용해서 권한 부여
     */
    
    @Test
    @DisplayName("상세 히스토리")
    @WithMockUser(roles = "User")
    public void testDetail() throws Exception {

        // Mockito를 이용한 가짜 Authtication 생성
        //Authentication authentication = Mockito.mock(Authentication.class);

        // Fake 데이터
        HashMap<String, HistoryView> fakeHistoryData = new HashMap<>();
        fakeHistoryData.put("Test1",null);
        fakeHistoryData.put("Test2",null);

        Mockito.when(historyTableService.detail(Mockito.any(LocalDate.class), Mockito.any(Authentication.class))).thenReturn(fakeHistoryData);

        mockMvc.perform(MockMvcRequestBuilders.get("/history/{uploadDate}", "2023-08-10"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    /*
     * 잘못된 인자 전달 에러
     * - 인자를 전달하는데 두가지 방법이 존재
     * 1. Mockito.any(LocalDate.class) >> 구체적인 값을 전달 하는 건 아니다.
     * 2. LocalDate date = LocalDate.of(2023, 7, 6) >> 구체적인 값 전달 가능.
     */
}
