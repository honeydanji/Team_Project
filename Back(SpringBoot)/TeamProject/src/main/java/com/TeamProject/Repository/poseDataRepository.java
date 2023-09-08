package com.TeamProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.poseData;
import com.TeamProject.Dto.poseDataDTO;

public interface poseDataRepository extends JpaRepository<poseData, Integer>{
    
    @Query("SELECT " + 
            "NEW com.TeamProject.Dto.poseDataDTO(p.objectId, p.x, p.y, p.z, p.rx, p.ry, p.rz) " + 
            "FROM poseData p " + 
            "WHERE p.historyId = ?1")
    List<poseDataDTO> sixPoseData(historyTable historyId);
}
