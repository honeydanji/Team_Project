package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TeamProject.Domain.HistoryTable;
import com.TeamProject.Domain.TwoSegmentationImage;

public interface TwoSegmentationRepository extends JpaRepository<TwoSegmentationImage, Integer> {
    String findTwoSegmentationPathByHistoryId(HistoryTable his);
    TwoSegmentationImage findByHistoryId(HistoryTable his);
}
