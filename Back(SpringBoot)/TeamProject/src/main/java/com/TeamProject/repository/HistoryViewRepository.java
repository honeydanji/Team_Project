package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TeamProject.Domain.HistoryView;

public interface HistoryViewRepository extends JpaRepository<HistoryView, Integer> {
    HistoryView findByHistoryId(Integer hitoryId);
}
