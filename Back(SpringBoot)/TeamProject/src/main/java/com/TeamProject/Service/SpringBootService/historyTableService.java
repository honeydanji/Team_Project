package com.TeamProject.Service.SpringBootService;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.historyView;
import com.TeamProject.Domain.members;
import com.TeamProject.Repository.historyTableRepository;
import com.TeamProject.Repository.historyViewRepository;
import com.TeamProject.Repository.membersRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class historyTableService {

    private final historyTableRepository historytablerepository; // history(상세시간)
    private final membersRepository membersrepository; // member(유저이메일)
    private final historyViewRepository historyviewrepository;

    @Transactional
    public historyTable historyUpdate(Authentication authentication) {

        // 유저번호
        members m = membersrepository.findByloginEmail(authentication.getName());
        
        historyTable historytable = new historyTable();
        
        historytable.setUserId(m);
        historytable.setUploadTime(Time.valueOf(LocalTime.now()));
        historytable.setUploadDate(LocalDate.now());

        historytablerepository.save(historytable);

        return historytable;
    }

    // 로그인 유저 게시글번호 및 업로드날짜 반환
    public Map<Object, Map<Object, Object>> historyUpdateDate(Authentication authentication) {
        String userEmail = authentication.getName();
        members userId = membersrepository.findByloginEmail(userEmail);

        if(userEmail == null){
            return null;
        }else {
            historytablerepository.historyIdByUserEmail(userId); // 로그인 유저 historyId 가져오기
            historytablerepository.uploadDateByUserEmail(userId); // 로그인 유저 파일 uploadDate 가져오기

            Map<Object, Map<Object, Object>> map = new HashMap<>();
            Map<Object, Object> miniMap = new HashMap<>();

            for(int i = 0; i < historytablerepository.historyIdByUserEmail(userId).size(); i++) {
                miniMap.put(historytablerepository.uploadDateByUserEmail(userId).get(i), historyviewrepository.findByHistoryId(historytablerepository.historyIdByUserEmail(userId).get(i)));
                map.put(historytablerepository.historyIdByUserEmail(userId).get(i), miniMap);
            }
            return map;
        }        
    }

    // 게시글 상세보기(게시글번호, 업로드날짜)
    public HashMap<String, historyView> detail(LocalDate uploadDate, Authentication authentication) {
        HashMap<String, historyView> map = new HashMap<String, historyView>();

        // userId 출력
        members userId = membersrepository.findByLoginEmail(authentication.getName());
    
        // 날짜, userId 출력
        List<Integer> historyId = historytablerepository.findByUploadDateANDUserId(uploadDate, userId);

        for(int i = 0; i < historytablerepository.findByUploadDateANDUserId(uploadDate, userId).size(); i++) {
            historyView a = historyviewrepository.findByHistoryId(historyId.get(i));
            map.put(Integer.toString(i), a);
        }
        return map;
        
    }

    // //  member All 히스토리
    // public List<historyTable> allData(Authentication authentication) {
    //     String userEmail = authentication.getName();
    //     members userId = membersrepository.findByloginEmail(userEmail);

    //     if(userEmail == null){
    //         return null;
    //     }else {
    //         return historytablerepository.findByUserId(userId);
    //     }        
    // }
     
}

