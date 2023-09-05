package com.TeamProject.Service.SpringBootService;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.members;
import com.TeamProject.Repository.commentsRepository;
import com.TeamProject.Repository.historyTableRepository;
import com.TeamProject.Repository.imageUploadRepository;
import com.TeamProject.Repository.membersRepository;
import com.TeamProject.Repository.twoSegmentationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class historyTableService {

    private final historyTableRepository historytablerepositroy; // history(상세시간)
    private final membersRepository membersrepository; // member(유저이메일)
    private final imageUploadRepository imageuploadrepository; // 원본
    private final twoSegmentationRepository twosegmentationrepository; // seg
    private final commentsRepository commentsrepository; // 코멘트

    @Transactional
    public historyTable historyUpdate(Authentication authentication) {

        // 유저번호
        members m = membersrepository.findByloginEmail(authentication.getName());
        
        historyTable historytable = new historyTable();
        
        historytable.setUserId(m);
        historytable.setUploadTime(Time.valueOf(LocalTime.now()));
        historytable.setUploadDate(LocalDate.now());

        historytablerepositroy.save(historytable);

        return historytable;
    }

    // 로그인 유저 게시글번호 및 업로드날짜 반환
    public HashMap<Integer, LocalDate> historyUpdateDate(Authentication authentication) {
        String userEmail = authentication.getName();
        members userId = membersrepository.findByloginEmail(userEmail);

        if(userEmail == null){
            return null;
        }else {
            historytablerepositroy.historyIdByUserEmail(userId); // 로그인 유저 historyId 가져오기
            historytablerepositroy.uploadDateByUserEmail(userId); // 로그인 유저 파일 uploadDate 가져오기

            HashMap<Integer, LocalDate> map = new HashMap<>();

            for(int i = 0; i < historytablerepositroy.historyIdByUserEmail(userId).size(); i++) {
                map.put(historytablerepositroy.historyIdByUserEmail(userId).get(i), historytablerepositroy.uploadDateByUserEmail(userId).get(i));
            }
            return map;
        }        
    }

    // 게시글 상세보기(게시글번호, 업로드날짜)
    public ResponseEntity<List<Object>> detail(Integer historyId, Authentication authentication) {
        // historyId에 해당하는 보여주고하 하는 모든 정보를 리스트에 저장
        String userEmail = authentication.getName();
        historyTable his = historytablerepositroy.findByHistoryId(historyId);

        if(userEmail == null){
            return null;
        }else {
           List<Object> historyList = new ArrayList<Object>();
           historyList.add(historytablerepositroy.timeByHistoryId(historyId)); // 상세시간
           historyList.add(userEmail); // 유저이메일
           historyList.add(membersrepository.nameByLoginEmail(userEmail));
           historyList.add(imageuploadrepository.originalByHistoryId(his)); // 원본이미지
           historyList.add(twosegmentationrepository.segmentationByHistoryId(his)); // Seg이미지
           historyList.add(commentsrepository.contentsByHistoryId(his)); // comment
           return ResponseEntity.ok(historyList);
        }
    }
}

