package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.twoOriginalImage;

public interface imageUploadRepository extends JpaRepository<twoOriginalImage, Integer> {

    @Query("SELECT t.twoOriginalPath FROM twoOriginalImage t WHERE historyId = ?1")
    String originalByHistoryId(historyTable his);
}
