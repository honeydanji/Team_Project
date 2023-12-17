package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TeamProject.Domain.HistoryTable;
import com.TeamProject.Domain.TwoOriginalImage;

public interface ImageUploadRepository extends JpaRepository<TwoOriginalImage, Integer> {
    String findTwoOriginalPathByHistoryId(HistoryTable his);
}
