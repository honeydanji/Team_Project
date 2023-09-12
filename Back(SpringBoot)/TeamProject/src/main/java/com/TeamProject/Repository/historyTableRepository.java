package com.TeamProject.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.members;

public interface historyTableRepository extends JpaRepository<historyTable ,Integer> {

    historyTable findByHistoryId(Integer historyId);

    @Query("SELECT h.uploadDate FROM historyTable h WHERE userId = ?1")
    List<LocalDate> uploadDateByUserEmail(members userId);

    @Query("SELECT h.historyId FROM historyTable h WHERE userId = ?1")
    List<Integer> historyIdByUserEmail(members userId);

    @Query("SELECT h.uploadTime FROM historyTable h WHERE historyId = ?1")
    Time timeByHistoryId(Integer historyId);

    @Query("SELECT h.historyId FROM historyTable h WHERE uploadDate = ?1 AND userId = ?2")
    List<Integer> findByUploadDateANDUserId(LocalDate upLocalDate, members userId);

    @Query("SELECT count(h.userId) FROM historyTable h WHERE userId = ?1")
    int countByUserId(members userId);
}
