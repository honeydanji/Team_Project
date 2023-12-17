package com.TeamProject.Service.SpringBootService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TeamProject.Domain.HistoryTable;
import com.TeamProject.Domain.PoseData;
import com.TeamProject.Dto.PoseDataDTO;
import com.TeamProject.Repository.PoseDataRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PoseDataService {

    private final PoseDataRepository posedatarepository;

    public void poseDataUpload(PoseDataDTO posedatadto, HistoryTable history) {
        PoseData posedata = new PoseData();

        posedata.setObjectId(posedatadto.getObjectId());
        posedata.setX(posedatadto.getX());
        posedata.setY(posedatadto.getY());
        posedata.setZ(posedatadto.getZ());
        posedata.setRx(posedatadto.getRx());
        posedata.setRy(posedatadto.getRy());
        posedata.setRz(posedatadto.getRz());
        posedata.setHistoryId(history);

        posedatarepository.save(posedata);
    }

    public List<PoseDataDTO> poseDataDispaly(HistoryTable historyId) {
        return posedatarepository.sixPoseData(historyId);
    }
    
}
