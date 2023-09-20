package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.HistoryTable;
import com.TeamProject.Domain.TwoSegmentationImage;

public interface TwoSegmentationRepository extends JpaRepository<TwoSegmentationImage, Integer> {

    @Query("SELECT t.twoSegmentationPath FROM TwoSegmentationImage t WHERE historyId = ?1")
    String segmentationByHistoryId(HistoryTable his);

    TwoSegmentationImage findByHistoryId(HistoryTable his);
}
