package com.TeamProject.Dto;

import com.TeamProject.Domain.twoSegmentationImage;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class twoSegmentationCoordinatesDTO {

    private Integer twoCoordinateId;
    private String twoObjectId;
    private double twoObjectAcc;
    private String xList;
    private String yList;
    private twoSegmentationImage twoSegmentationId;
    
}