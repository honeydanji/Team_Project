package com.TeamProject.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.TeamProject.Domain.Members;
import com.TeamProject.Dto.MembersDTO;
import com.TeamProject.Repository.MembersRepository;
import com.TeamProject.Service.SpringBootService.MemberService;

@ExtendWith(MockitoExtension.class) // Mockito 초기화를 도와줌
public class MemberServiceTest {
    
    @Mock
    private MembersRepository membersRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @DisplayName("회원가입 테스트")
    public void testRegisterService() {
        // 가짜 MemberDTO 객체 생성
        MembersDTO fakeMemberDTO = new MembersDTO();
        fakeMemberDTO.setName("Test");
        fakeMemberDTO.setLoginEmail("Test3680@test.com");
        fakeMemberDTO.setPassword("test");
        fakeMemberDTO.setConfirmPassword("test");
        fakeMemberDTO.setPhoneNumber("010-0000-0000");

        // 가짜 회원 객체생성 및 반환 설정
        Members fakeMember = new Members();
        Mockito.when(bCryptPasswordEncoder.encode(fakeMemberDTO.getPassword())).thenReturn("encodedPassword");
        Mockito.when(membersRepository.save(Mockito.any())).thenReturn(fakeMember);

        // MemberService 객체 생성
        MemberService memberService = new MemberService(membersRepository, bCryptPasswordEncoder);

        // registerService 메서드 호출
        ResponseEntity<String> responseEntity = memberService.registerService(fakeMemberDTO);

        // 결과 검증
        assertEquals("회원가입 성공.", responseEntity.getBody());
    }

    @Test
    @DisplayName("비밀번호와 확인비밀번호가 일치 하지 않는 경우")
    public void testRegisterService_PasswordMismatch() {
        // 가짜 MembersDTO 객체 생성
        MembersDTO fakeMembersDTO = new MembersDTO();
        fakeMembersDTO.setPassword("password");
        fakeMembersDTO.setConfirmPassword("mismatchedPassword");

        // MemberService 객체 생성
        MemberService memberService = new MemberService(membersRepository, bCryptPasswordEncoder);

        // registerService 호출
        ResponseEntity<String> responseEntity = memberService.registerService(fakeMembersDTO);

        // 결과 검증
        assertEquals("비밀번호를 확인해주세요.", responseEntity.getBody());
    }

    @Test
    @DisplayName("이미 존재하는 이메일로 가입할 경우")
    public void testRegisterService_DuplicateEmail() {
        // 가짜 MembersDTO 객체 생성
        MembersDTO fakeMemberDTO = new MembersDTO();
        fakeMemberDTO.setLoginEmail("test@test.com");
        fakeMemberDTO.setPassword("test");
        fakeMemberDTO.setConfirmPassword("test");

        // 이미 존재하는 이메일 주소로 회원 조회를 시도하도록 설정
        Mockito.when(membersRepository.findByLoginEmail(fakeMemberDTO.getLoginEmail())).thenReturn(new Members());

        // MemberService 객체 생성
        MemberService memberService = new MemberService(membersRepository, bCryptPasswordEncoder);

        // registerService 메서드 호출
        ResponseEntity<String> responseEntity = memberService.registerService(fakeMemberDTO);

        // 결과 검증
        assertEquals("이미 존재하는 이메일입니다.", responseEntity.getBody());
    }
}
/*
 * @ExtendWith란?
 * SpringBoot와 무관한 Mockito에서 제공하는 단위 테스트 어노테이션
 * 서비스와 같은 개별 컴포넌트를 테스트할 때 사용
 * 
 * @ExtendWith(MockitoExtension.class) 사용이유
 * Mockito와 JUnit5와 통합하여 테스트를 가능하게 한다.
 * 
 * JUnit5 : 기본적인 테스트용 프레임워크
 * Mockito : JUnit5의 기능을 확장 시키는?? 느낌의 프레임 워크
 * 
 * Mockito를 이용하면 의존성을 쉽게 해결할 수 있다.(가짜객체생성!)
 */
