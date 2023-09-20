package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.HistoryTable;
import com.TeamProject.Domain.TwoOriginalImage;

public interface ImageUploadRepository extends JpaRepository<TwoOriginalImage, Integer> {

    @Query("SELECT t.twoOriginalPath FROM twoOriginalImage t WHERE historyId = ?1")
    String originalByHistoryId(HistoryTable his);
}
