package com.TeamProject.Service.SpringBootService;

import java.time.LocalDate;

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

        historytable.setUploadDate(LocalDate.now());

        historytablerepositroy.save(historytable);

        return historytable;
    }
}
