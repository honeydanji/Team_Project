package com.TeamProject.Service.SpringBootService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.TeamProject.Domain.comments;
import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.members;
import com.TeamProject.Repository.commentsRepository;
import com.TeamProject.Repository.historyTableRepository;
import com.TeamProject.Repository.membersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class commentsService {

    private final commentsRepository commentsrepository; // 코멘트테이블
    private final historyTableRepository historytablerepository; // 히스토리테이블
    private final membersRepository membersrepository; // 멤버테이블

    public ResponseEntity<String> commentUpload(int historyId, String comment, Authentication authentication) {

        String userEmail = authentication.getName(); // 로그인 ID

        // 히스토리아이디로 가져온 userId와 실제 userId가 일치하는 지 확인
        members hisId = historytablerepository.findByHistoryId(historyId).getUserId();
        members memId = membersrepository.findByloginEmail(userEmail); 

        if (userEmail == null) {
            return ResponseEntity.ok("권한이 없습니다.");
        }else if(hisId.equals(memId)) {
            historyTable his = historytablerepository.findByHistoryId(historyId);

            comments com = new comments();

            com.setContents(comment);
            com.setHistoryId(his);

            commentsrepository.save(com);

            return ResponseEntity.ok("글 작성이 완료되었습니다.");
        }else{
            return null;
        }
    }
}
