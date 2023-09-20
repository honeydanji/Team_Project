package com.TeamProject.Service.SpringBootService;

import org.springframework.stereotype.Service;

import com.TeamProject.Domain.TwoSegmentationImage;
import com.TeamProject.Dto.TwoSegmentationImageDTO;
import com.TeamProject.Repository.TwoSegmentationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TwoSegmentationImageService {

    private final TwoSegmentationRepository twosegmentationrepository;

    @Transactional
    public TwoSegmentationImage segmentationImage(TwoSegmentationImageDTO twosegmentationimagedto) {

        TwoSegmentationImage twosegmentationimage = new TwoSegmentationImage();

        twosegmentationimage.setTwoSegmentationPath(twosegmentationimagedto.getTwoSegmentationPath());
        twosegmentationimage.setHistoryId(twosegmentationimagedto.getHistoryId());

        return twosegmentationrepository.save(twosegmentationimage);
    }    
}
