package com.TeamProject.Service.SpringBootService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TeamProject.Domain.historyTable;
import com.TeamProject.Domain.twoSegmentationCoordinates;
import com.TeamProject.Domain.twoSegmentationImage;
import com.TeamProject.Dto.boxInfoDTO;
import com.TeamProject.Dto.twoSegmentationCoordinatesDTO;
import com.TeamProject.Repository.twoSegmentationCoordinatesRepository;
import com.TeamProject.Repository.twoSegmentationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class twoSegmentationCoordinatesService {
    
    private final twoSegmentationCoordinatesRepository twosegmentationcoordinatesrepository;

    private final twoSegmentationRepository twosegmentationrepository;

    @Transactional
    public void twoCoordinates(twoSegmentationCoordinatesDTO twosegmentationcorrdinatesdto) {

        twoSegmentationCoordinates twosegmentationcoordinates = new twoSegmentationCoordinates();

        twosegmentationcoordinates.setTwoObjectAcc(twosegmentationcorrdinatesdto.getTwoObjectAcc());
        twosegmentationcoordinates.setTwoObjectId(twosegmentationcorrdinatesdto.getTwoObjectId());
        twosegmentationcoordinates.setXList(twosegmentationcorrdinatesdto.getXList());
        twosegmentationcoordinates.setYList(twosegmentationcorrdinatesdto.getYList());
        twosegmentationcoordinates.setXBox(twosegmentationcorrdinatesdto.getXBox());
        twosegmentationcoordinates.setYBox(twosegmentationcorrdinatesdto.getYBox());
        twosegmentationcoordinates.setWidth(twosegmentationcorrdinatesdto.getWidth());
        twosegmentationcoordinates.setHeight(twosegmentationcorrdinatesdto.getHeight());
        twosegmentationcoordinates.setTwoSegmentationId(twosegmentationcorrdinatesdto.getTwoSegmentationId());
        twosegmentationcoordinatesrepository.save(twosegmentationcoordinates);
    }

    public List<Object[]> segResponseAll() {
        return twosegmentationcoordinatesrepository.findBy();
    }

    // boxInfo 반환
    public List<boxInfoDTO> boxInfo(historyTable history) {
        // 히스토리 아이디에 해당하는 세그멘테이션 아이디 추출
        twoSegmentationImage twosegmentationid =  twosegmentationrepository.findByHistoryId(history);

        return twosegmentationcoordinatesrepository.boxInfo(twosegmentationid);
    }




}
