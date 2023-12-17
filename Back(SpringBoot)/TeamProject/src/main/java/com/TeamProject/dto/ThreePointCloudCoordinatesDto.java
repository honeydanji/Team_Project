package com.TeamProject.Dto;

import com.TeamProject.Domain.ThreeOriginalPointCloud;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreePointCloudCoordinatesDTO {

    private String threeObjectId;
    private String xList;
    private String yList;
    private String zList;
    private ThreeOriginalPointCloud threeOriginalId;   
}
