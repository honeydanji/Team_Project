package com.TeamProject.Service.SpringBootService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TeamProject.Domain.twoSegmentationCoordinates;
import com.TeamProject.Dto.twoSegmentationCoordinatesDTO;
import com.TeamProject.Repository.twoSegmentationCoordinatesRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class twoSegmentationCoordinatesService {
    
    private final twoSegmentationCoordinatesRepository twosegmentationcoordinatesrepository;

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


}
