package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TeamProject.Domain.historyTable;

public interface historyTableRepository extends JpaRepository<historyTable ,Integer> {
    
}
