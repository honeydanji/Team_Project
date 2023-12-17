package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TeamProject.Domain.HistoryTable;
import com.TeamProject.Domain.ThreeOriginalPointCloud;

public interface ThreeOriginalPointCloudRepository extends JpaRepository<ThreeOriginalPointCloud, Integer> {
    ThreeOriginalPointCloud findByHistoryId(HistoryTable history);
}
