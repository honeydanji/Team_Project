package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.threeOriginalPointCloud;

public interface threeOriginalPointCloudRepository extends JpaRepository<threeOriginalPointCloud, Integer> {
    threeOriginalPointCloud findByHistoryId(historyTable history);
}
