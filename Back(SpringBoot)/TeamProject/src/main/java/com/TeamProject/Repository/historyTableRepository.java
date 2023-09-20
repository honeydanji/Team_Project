package com.TeamProject.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.HistoryTable;
import com.TeamProject.Domain.Members;

public interface HistoryTableRepository extends JpaRepository<HistoryTable ,Integer> {

    HistoryTable findByHistoryId(Integer historyId);

    List<HistoryTable> findByUserIdAndUploadDate(Members userId, LocalDate date); // 수정


    @Query("SELECT h.uploadDate FROM HistoryTable h WHERE userId = ?1")
    List<LocalDate> uploadDateByUserEmail(Members userId);

    @Query("SELECT h.historyId FROM HistoryTable h WHERE userId = ?1")
    List<Integer> historyIdByUserEmail(Members userId);

    @Query("SELECT h.uploadTime FROM HistoryTable h WHERE historyId = ?1")
    Time timeByHistoryId(Integer historyId);

    @Query("SELECT h.historyId FROM HistoryTable h WHERE uploadDate = ?1 AND userId = ?2")
    List<Integer> findByUploadDateANDUserId(LocalDate upLocalDate, Members userId);

    @Query("SELECT count(h.userId) FROM HistoryTable h WHERE userId = ?1")
    int countByUserId(Members userId);
}
