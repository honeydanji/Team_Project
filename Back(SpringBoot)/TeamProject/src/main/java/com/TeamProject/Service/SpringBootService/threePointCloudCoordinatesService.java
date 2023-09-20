package com.TeamProject.Service.SpringBootService;

import org.springframework.stereotype.Service;

import com.TeamProject.Domain.ThreePointCloudCoordinates;
import com.TeamProject.Dto.ThreePointCloudCoordinatesDTO;
import com.TeamProject.Repository.ThreePointCloudCoordinatesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThreePointCloudCoordinatesService {

    private final ThreePointCloudCoordinatesRepository threepointcloudcoordinatesrepository;    

    public void threeCoordinates(ThreePointCloudCoordinatesDTO threepointcloudcoordinatesdto) {

        ThreePointCloudCoordinates threepointcloudcoordinates = new ThreePointCloudCoordinates();

        threepointcloudcoordinates.setThreeObjectId(threepointcloudcoordinatesdto.getThreeObjectId());
        threepointcloudcoordinates.setXList(threepointcloudcoordinatesdto.getXList());
        threepointcloudcoordinates.setYList(threepointcloudcoordinatesdto.getYList());
        threepointcloudcoordinates.setZList(threepointcloudcoordinatesdto.getZList());
        threepointcloudcoordinates.setThreeOriginalId(threepointcloudcoordinatesdto.getThreeOriginalId());
        threepointcloudcoordinatesrepository.save(threepointcloudcoordinates);
    }

}
