package com.TeamProject.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TeamProject.Domain.HistoryTable;
import com.TeamProject.Domain.Members;

public interface HistoryTableRepository extends JpaRepository<HistoryTable ,Integer> {

    HistoryTable findByHistoryId(Integer historyId);
    List<HistoryTable> findByUserIdAndUploadDate(Members userId, LocalDate date); // 수정
    List<LocalDate> findUploadDateByUserId(Members userId);
    List<Integer> findHistoryIdByUserId(Members userId);
    Time findTimeByHistoryId(Integer historyId);
    List<Integer> findByUploadDateAndUserId(LocalDate uploadDate, Members userId);
    int countByUserId(Members userId);
}
