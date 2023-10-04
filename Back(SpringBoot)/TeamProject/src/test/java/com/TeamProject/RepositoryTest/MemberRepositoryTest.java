package com.TeamProject.RepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.TeamProject.Domain.Members;
import com.TeamProject.Repository.MembersRepository;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // 테스트 메서드 실행 후 컨텍스트 정리
public class MemberRepositoryTest {

    @Autowired
    private MembersRepository membersRepository;

    @Test
    @DisplayName("멤버 생성이 잘 되는 지 확인")
    public void registerMember() {
        // 가짜 회원 객체 생성 및 저장
        Members member = new Members();
        member.setLoginEmail("test3680@naver.com");
        member.setName("test");
        member.setPassword("test");
        member.setConfirmPassword("test");
        member.setPhoneNumber("test");
        member.setRole("ROLE_TEST");
        membersRepository.save(member);

        // findByLoginEmail 테스트
        Members foundMember = membersRepository.findByLoginEmail("test3680@naver.com");

        // 조회된 회원과 예상 결과를 비교 검증
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getLoginEmail()).isEqualTo("test3680@naver.com");
        assertThat(foundMember.getName()).isEqualTo("test");
    }   
}

/*
 * @DataJpaTest 기능
 * - JPA 관련된 설정만 로드한다. (WebMVC와 관련된 Bean이나 기능은 로드되지 않는다)
 * - JPA를 사용해서 생성/조회/수정/삭제 기능의 테스트가 가능하다.
 * - @Transactional을 기본적으로 내장하고 있으므로, 매 테스트 코드가 종료되면 자동으로 DB가 롤백된다.
 * - 기본적으로 내장 DB를 사용하는데, 설정을 통해 실제 DB로 테스트도 가능하다.
 * - @Entity가 선언된 클래스를 스캔하여 저장소를 구성한다.
 */