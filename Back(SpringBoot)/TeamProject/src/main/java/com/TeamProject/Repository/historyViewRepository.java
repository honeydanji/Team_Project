package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TeamProject.Domain.historyView;

public interface historyViewRepository extends JpaRepository<historyView, Integer> {
    historyView findByHistoryId(Integer hitoryId);
}
