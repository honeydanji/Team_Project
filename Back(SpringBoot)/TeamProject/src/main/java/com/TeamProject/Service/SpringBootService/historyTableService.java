package com.TeamProject.Service.SpringBootService;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Repository.historyTableRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class historyTableService {

    private final historyTableRepository historytablerepositroy;

    @Transactional
    public historyTable historyUpdate() {

        historyTable historytable = new historyTable();

        historytable.setUploadTime(Time.valueOf(LocalTime.now()));
        historytable.setUploadDate(LocalDate.now());

        historytablerepositroy.save(historytable);

        return historytable;
    }
}
