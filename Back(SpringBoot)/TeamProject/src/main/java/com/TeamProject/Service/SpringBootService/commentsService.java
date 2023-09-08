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

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class commentsService {

    private final commentsRepository commentsrepository; // 코멘트테이블
    private final historyTableRepository historytablerepository; // 히스토리테이블
    private final membersRepository membersrepository; // 멤버테이블

    @Transactional
    public ResponseEntity<String> commentUpload(int historyId, String contents, Authentication authentication) {

        String userEmail = authentication.getName(); // 로그인 ID

        // 히스토리아이디로 가져온 userId와 실제 userId가 일치하는 지 확인
        members hisId = historytablerepository.findByHistoryId(historyId).getUserId();
        members memId = membersrepository.findByloginEmail(userEmail); 

        if (userEmail == null) {
            return ResponseEntity.ok("권한이 없습니다.");
        }else if(hisId.equals(memId)) {

            if(commentsrepository.findAll() != null) {
                return ResponseEntity.ok("코멘트는 1개만 작성가능.");
            }

            historyTable his = historytablerepository.findByHistoryId(historyId);

            comments com = new comments();

            com.setComment(contents);
            com.setHistoryId(his);

            commentsrepository.save(com);

            return ResponseEntity.ok("글 작성이 완료되었습니다.");
        }else{
            return null;
        }
    }

    @Transactional
    public ResponseEntity<String> commentUpdate(int historyId, String contents, Authentication authentication) {

        String userEmail = authentication.getName(); // 로그인 ID

        // 히스토리아이디로 가져온 userId와 실제 userId가 일치하는 지 확인
        members hisId = historytablerepository.findByHistoryId(historyId).getUserId();
        members memId = membersrepository.findByloginEmail(userEmail); 

        if (userEmail == null) {
            return ResponseEntity.ok("권한이 없습니다.");
        }else if(hisId.equals(memId)) {
            comments com = commentsrepository.findByHistoryId(historytablerepository.findByHistoryId(historyId));

            com.setComment(contents);

            commentsrepository.save(com);

            return ResponseEntity.ok("글 수정이 완료되었습니다.");
        }else{
            return null;
        }
    }

    @Transactional
    public ResponseEntity<String> commentDelete(int historyId, Authentication authentication) {

        String userEmail = authentication.getName(); // 로그인 ID

        // 히스토리아이디로 가져온 userId와 실제 userId가 일치하는 지 확인
        members hisId = historytablerepository.findByHistoryId(historyId).getUserId();
        members memId = membersrepository.findByloginEmail(userEmail); 

        if (userEmail == null) {
            return ResponseEntity.ok("권한이 없습니다.");
        }else if(hisId.equals(memId)) {
            commentsrepository.deleteByHistoryId(historytablerepository.findByHistoryId(historyId));
            return ResponseEntity.ok("글 삭제가 완료되었습니다.");
        }else{
            return null;
        }
    }
}
