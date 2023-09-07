package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.comments;
import com.TeamProject.Domain.historyTable;

public interface commentsRepository extends JpaRepository<comments, Integer> {

    @Query("SELECT c.contents FROM comments c WHERE historyId = ?1")
    String contentsByHistoryId(historyTable historyId);

    comments findByHistoryId(historyTable historyId);

    void deleteByHistoryId(historyTable historyId);
}
