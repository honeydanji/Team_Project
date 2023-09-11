package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.twoSegmentationImage;

public interface twoSegmentationRepository extends JpaRepository<twoSegmentationImage, Integer> {

    @Query("SELECT t.twoSegmentationPath FROM twoSegmentationImage t WHERE historyId = ?1")
    String segmentationByHistoryId(historyTable his);

    twoSegmentationImage findByHistoryId(historyTable his);
}
