package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.Comments;
import com.TeamProject.Domain.HistoryTable;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    @Query("SELECT c.comment FROM comments c WHERE historyId = ?1")
    String commentByHistoryId(HistoryTable historyId);

    Comments findByHistoryId(HistoryTable historyId);

    void deleteByHistoryId(HistoryTable historyId);
}
