package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TeamProject.Domain.Comments;
import com.TeamProject.Domain.HistoryTable;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    String findCommentByHistoryId(HistoryTable historyId);

    Comments findByHistoryId(HistoryTable historyId);

    void deleteByHistoryId(HistoryTable historyId);
}
