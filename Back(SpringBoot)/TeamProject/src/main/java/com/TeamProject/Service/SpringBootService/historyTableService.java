package com.TeamProject.Service.SpringBootService;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.members;
import com.TeamProject.Repository.historyTableRepository;
import com.TeamProject.Repository.membersRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class historyTableService {

    private final historyTableRepository historytablerepositroy;
    private final membersRepository membersrepository;

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
}
