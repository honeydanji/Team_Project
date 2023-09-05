package com.TeamProject.Service.SpringBootService;

import org.springframework.stereotype.Service;

import com.TeamProject.Domain.threePointCloudCoordinates;
import com.TeamProject.Dto.threePointCloudCoordinatesDTO;
import com.TeamProject.Repository.threePointCloudCoordinatesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class threePointCloudCoordinatesService {

    private final threePointCloudCoordinatesRepository threepointcloudcoordinatesrepository;    

    public void threeCoordinates(threePointCloudCoordinatesDTO threepointcloudcoordinatesdto) {

        threePointCloudCoordinates threepointcloudcoordinates = new threePointCloudCoordinates();

        threepointcloudcoordinates.setThreeObjectId(threepointcloudcoordinatesdto.getThreeObjectId());
        threepointcloudcoordinates.setXList(threepointcloudcoordinatesdto.getXList());
        threepointcloudcoordinates.setYList(threepointcloudcoordinatesdto.getYList());
        threepointcloudcoordinates.setZList(threepointcloudcoordinatesdto.getZList());
        threepointcloudcoordinates.setThreeOriginalId(threepointcloudcoordinatesdto.getThreeOriginalId());
        threepointcloudcoordinatesrepository.save(threepointcloudcoordinates);
    }

}
