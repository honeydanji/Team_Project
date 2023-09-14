package com.TeamProject.Service.SpringBootService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.poseData;
import com.TeamProject.Dto.poseDataDTO;
import com.TeamProject.Repository.poseDataRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class poseDataService {

    private final poseDataRepository posedatarepository;

    public void poseDataUpload(poseDataDTO posedatadto, historyTable history) {
        poseData posedata = new poseData();

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

    public List<poseDataDTO> poseDataDispaly(historyTable historyId) {
        return posedatarepository.sixPoseData(historyId);
    }
    
}
