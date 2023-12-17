package com.TeamProject.Service.SpringBootService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TeamProject.Domain.HistoryTable;
import com.TeamProject.Domain.TwoSegmentationCoordinates;
import com.TeamProject.Domain.TwoSegmentationImage;
import com.TeamProject.Dto.BoxInfoDTO;
import com.TeamProject.Dto.TwoSegmentationCoordinatesDTO;
import com.TeamProject.Repository.TwoSegmentationCoordinatesRepository;
import com.TeamProject.Repository.TwoSegmentationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TwoSegmentationCoordinatesService {
    
    private final TwoSegmentationCoordinatesRepository twosegmentationcoordinatesrepository;

    private final TwoSegmentationRepository twosegmentationrepository;

    @Transactional
    public void twoCoordinates(TwoSegmentationCoordinatesDTO twosegmentationcorrdinatesdto) {

        TwoSegmentationCoordinates twosegmentationcoordinates = new TwoSegmentationCoordinates();

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
    public List<BoxInfoDTO> boxInfo(HistoryTable history) {
        // 히스토리 아이디에 해당하는 세그멘테이션 아이디 추출
        TwoSegmentationImage twosegmentationid =  twosegmentationrepository.findByHistoryId(history);

        return twosegmentationcoordinatesrepository.boxInfo(twosegmentationid);
    }




}
