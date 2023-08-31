package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.twoSegmentationImage;

public interface twoSegmentationRepository extends JpaRepository<twoSegmentationImage, Integer> {

    @Query("SELECT t.twoSegmentationId FROM twoSegmentationImage t WHERE t.historyId = ?1")
    twoSegmentationImage findByTwoDSegmentationId(historyTable historyId);
}
