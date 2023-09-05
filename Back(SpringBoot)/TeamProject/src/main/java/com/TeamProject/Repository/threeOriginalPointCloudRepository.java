package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.threeOriginalPointCloud;

public interface threeOriginalPointCloudRepository extends JpaRepository<threeOriginalPointCloud, Integer> {

    // @Query("SELECT * FROM threeOriginalPointCloud t WHERE historyId =?1")
    // threeOriginalPointCloud originalIdByHistoryId(historyTable history);

    threeOriginalPointCloud findByHistoryId(historyTable history);
}
