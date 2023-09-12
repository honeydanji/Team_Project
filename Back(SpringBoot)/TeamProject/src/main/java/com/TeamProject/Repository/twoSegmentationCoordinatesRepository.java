package com.TeamProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.twoSegmentationCoordinates;
import com.TeamProject.Domain.twoSegmentationImage;
import com.TeamProject.Dto.boxInfoDTO;

public interface twoSegmentationCoordinatesRepository extends JpaRepository<twoSegmentationCoordinates, Integer> {
    
    @Query("SELECT c.twoObjectId, c.twoObjectAcc FROM twoSegmentationCoordinates c")
    List<Object[]> findBy();

    @Query("SELECT " + 
            "NEW com.TeamProject.Dto.boxInfoDTO(c.twoObjectId, c.xBox, c.yBox, c.width, c.height) " + 
            "FROM twoSegmentationCoordinates c " + 
            "WHERE twoSegmentationId = ?1")
    List<boxInfoDTO> boxInfo(twoSegmentationImage twosegmentationid);

    @Query("SELECT count(c.twoObjectId) FROM twoSegmentationCoordinates c WHERE twoSegmentationId = ?1")
    int countBySegmentationId(twoSegmentationImage twosegmentationid);

    @Query("SELECT c.twoObjectId FROM twoSegmentationCoordinates c WHERE twoSegmentationId = ?1")
    List<String> twoObjecIdByTwoSegmentationId(twoSegmentationImage twosegmentationid);

    @Query("SELECT c.twoObjectAcc FROM twoSegmentationCoordinates c WHERE twoSegmentationId = ?1")
    List<Double> twoObjectAccBySegmentationId(twoSegmentationImage twosegmentationid);

}
