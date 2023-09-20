package com.TeamProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.HistoryTable;
import com.TeamProject.Domain.PoseData;
import com.TeamProject.Dto.PoseDataDTO;

public interface PoseDataRepository extends JpaRepository<PoseData, Integer>{
    
    @Query("SELECT " + 
            "NEW com.TeamProject.Dto.PoseDataDTO(p.objectId, p.x, p.y, p.z, p.rx, p.ry, p.rz) " + 
            "FROM PoseData p " + 
            "WHERE p.historyId = ?1")
    List<PoseDataDTO> sixPoseData(HistoryTable historyId);
}
