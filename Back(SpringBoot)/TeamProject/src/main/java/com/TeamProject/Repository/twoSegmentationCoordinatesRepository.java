package com.TeamProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.twoSegmentationCoordinates;

public interface twoSegmentationCoordinatesRepository extends JpaRepository<twoSegmentationCoordinates, Integer> {
    
    @Query("SELECT c.twoObjectId, c.twoObjectAcc FROM twoSegmentationCoordinates c")
    List<Object[]> findBy();
}
