package com.TeamProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.TwoSegmentationCoordinates;
import com.TeamProject.Domain.TwoSegmentationImage;
import com.TeamProject.Dto.BoxInfoDTO;

public interface TwoSegmentationCoordinatesRepository extends JpaRepository<TwoSegmentationCoordinates, Integer> {
    
    @Query("SELECT c.twoObjectId, c.twoObjectAcc FROM TwoSegmentationCoordinates c")
    List<Object[]> findBy();

    @Query("SELECT " + 
            "NEW com.TeamProject.Dto.BoxInfoDTO(c.twoObjectId, c.xBox, c.yBox, c.width, c.height) " + 
            "FROM TwoSegmentationCoordinates c " + 
            "WHERE twoSegmentationId = ?1")
    List<BoxInfoDTO> boxInfo(TwoSegmentationImage twosegmentationid);

    int countByTwoSegmentationId(TwoSegmentationImage twosegmentationid);
    List<String> findTwoObjecIdByTwoSegmentationId(TwoSegmentationImage twosegmentationid);
    List<Double> findTwoObjectAccByTwoSegmentationId(TwoSegmentationImage twosegmentationid);
}
