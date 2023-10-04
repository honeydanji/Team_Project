package com.TeamProject.Service.SpringBootService;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TeamProject.Domain.HistoryTable;
import com.TeamProject.Domain.HistoryView;
import com.TeamProject.Domain.Members;
import com.TeamProject.Domain.TwoSegmentationImage;
import com.TeamProject.Repository.HistoryTableRepository;
import com.TeamProject.Repository.HistoryViewRepository;
import com.TeamProject.Repository.MembersRepository;
import com.TeamProject.Repository.TwoSegmentationCoordinatesRepository;
import com.TeamProject.Repository.TwoSegmentationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HistoryTableService {

    private final HistoryTableRepository historytablerepository; // history(상세시간)
    private final MembersRepository membersrepository; // member(유저이메일)
    private final HistoryViewRepository historyviewrepository;
    private final TwoSegmentationRepository twosegmentationrepository; // segPath 불러오기
    private final TwoSegmentationCoordinatesRepository twosegmentationcoordinatesrepository;

    @Transactional
    public HistoryTable historyUpdate(Authentication authentication) {

        // 유저번호
        Members m = membersrepository.findByLoginEmail(authentication.getName());
        
        HistoryTable historytable = new HistoryTable();
        
        historytable.setUserId(m);
        historytable.setUploadTime(Time.valueOf(LocalTime.now()));
        historytable.setUploadDate(LocalDate.now());

        historytablerepository.save(historytable);

        return historytable;
    }

    // 로그인 유저 게시글번호 및 업로드날짜 반환
    public Map<Object, Object> historyUpdateDate(Authentication authentication) {
        String userEmail = authentication.getName();
        Members userId = membersrepository.findByLoginEmail(userEmail);

        if(userEmail == null){
            return null;
        }else {
            List<Integer> his = historytablerepository.historyIdByUserEmail(userId); // 로그인 유저 historyId 가져오기(Integer)
            Map<Object, Object> miniMap = new HashMap<>();
            
            for(int i = 0; i < historytablerepository.historyIdByUserEmail(userId).size(); i++) {
                miniMap.put(historytablerepository.uploadDateByUserEmail(userId).get(i), 
                            twosegmentationrepository.segmentationByHistoryId(historytablerepository.findByUserIdAndUploadDate(userId, historytablerepository.uploadDateByUserEmail(userId).get(i)).get(historytablerepository.findByUserIdAndUploadDate(userId, historytablerepository.uploadDateByUserEmail(userId).get(i)).size()-1))); // null > list
            }


            for(int i : his) {
                System.out.println(i);
            }
            return miniMap;
        }        
    }

    // 게시글 상세보기(게시글번호, 업로드날짜)
    public HashMap<String, HistoryView> detail(LocalDate uploadDate, Authentication authentication) {
        HashMap<String, HistoryView> map = new HashMap<String, HistoryView>();

        // userId 출력
        Members userId = membersrepository.findByLoginEmail(authentication.getName());
    
        // 날짜, userId 출력
        List<Integer> historyId = historytablerepository.findByUploadDateANDUserId(uploadDate, userId);

        for(int i = 0; i < historytablerepository.findByUploadDateANDUserId(uploadDate, userId).size(); i++) {
            HistoryView a = historyviewrepository.findByHistoryId(historyId.get(i));
            map.put(Integer.toString(i), a);
        }
        return map;
    }

    public Map<String, Object> results(LocalDate uploadDate, Authentication authentication) {
        Map<String, Object> map = new HashMap<>();
        // 1. 전체 클래스 중에서 각각의 클래스 비율
        //// 클래스 전부 리스트에 저장.
        //// 클래스 갯수 / 전체 갯수
         // 2. 정확도 출력하기
        // 0.5 ~ 0.6 잘 안된거
        // 0.7 ~ 잘 된거 >>>>>>> WHERE사용해서 리스트로 들고오고 for문을 통해서 리스트(가변) 저장하기.
        // 3. 전체 정확도 평균

        // userId 출력
        Members userId = membersrepository.findByLoginEmail(authentication.getName());
        int classCount = 0; // 총 클래스수
        double totalAcc = 0; // 총 정확도
        int bestItem = 0;   // 0.7 이상
        int worstItem = 0; // 0.7 미만
        int box = 0;
        int bongji = 0;
        int milk = 0;
        int energdrink = 0;
        int cansnack = 0;
    
        // 날짜, userId 출력
        List<Integer> historyId = historytablerepository.findByUploadDateANDUserId(uploadDate, userId);
        for(int i = 0; i < historytablerepository.findByUploadDateANDUserId(uploadDate, userId).size(); i++) {
            HistoryTable his = historytablerepository.findByHistoryId(historyId.get(i));
            TwoSegmentationImage segmentationId = twosegmentationrepository.findByHistoryId(his);
            classCount += twosegmentationcoordinatesrepository.countBySegmentationId(segmentationId);
            List<String> inputClass = twosegmentationcoordinatesrepository.twoObjecIdByTwoSegmentationId(segmentationId);
            List<Double> inputAcc = twosegmentationcoordinatesrepository.twoObjectAccBySegmentationId(segmentationId);
            for(int j = 0; j < inputClass.size(); j++) {
                switch(inputClass.get(j)) {
                    case "box" : box++; break;
                    case "bongji" : bongji++; break;
                    case "milk" : milk++; break;
                    case "energdrink" : energdrink++; break;
                    case "cansnack" : cansnack++; break;
                }
                totalAcc += inputAcc.get(j);
                if(inputAcc.get(j) >= 0.7) {
                    bestItem++;
                }else{
                    worstItem++;
                }
            }
        }

        map.put("classCount", classCount);
        map.put("box", box);
        map.put("bongji", bongji);
        map.put("milk", milk);
        map.put("energydrink", energdrink);
        map.put("cansnack", cansnack);
        map.put("totalAcc", Double.parseDouble(String.format("%.2f", totalAcc/classCount)));
        map.put("bestItem", bestItem);
        map.put("worstItem", worstItem);
        return map;
    }
}

