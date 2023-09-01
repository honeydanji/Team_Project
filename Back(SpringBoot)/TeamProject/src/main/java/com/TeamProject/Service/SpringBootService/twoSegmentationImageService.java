package com.TeamProject.Service.SpringBootService;

import org.springframework.stereotype.Service;

import com.TeamProject.Domain.twoSegmentationImage;
import com.TeamProject.Dto.twoSegmentationImageDTO;
import com.TeamProject.Repository.twoSegmentationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class twoSegmentationImageService {

    private final twoSegmentationRepository twosegmentationrepository;

    @Transactional
    public twoSegmentationImage segmentationImage(twoSegmentationImageDTO twosegmentationimagedto) {

        twoSegmentationImage twosegmentationimage = new twoSegmentationImage();

        twosegmentationimage.setTwoSegmentationPath(twosegmentationimagedto.getTwoSegmentationPath());
        twosegmentationimage.setHistoryId(twosegmentationimagedto.getHistoryId());

        return twosegmentationrepository.save(twosegmentationimage);
    }    
}
