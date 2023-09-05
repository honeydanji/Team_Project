package com.TeamProject.Dto;

import com.TeamProject.Domain.threeOriginalPointCloud;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class threePointCloudCoordinatesDTO {

    private String threeObjectId;
    private String xList;
    private String yList;
    private String zList;
    private threeOriginalPointCloud threeOriginalId;
    
}
