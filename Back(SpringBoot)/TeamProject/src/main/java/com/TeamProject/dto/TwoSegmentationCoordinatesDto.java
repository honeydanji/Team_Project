package com.TeamProject.Dto;

import com.TeamProject.Domain.TwoSegmentationImage;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TwoSegmentationCoordinatesDTO {

    private Integer twoCoordinateId;
    private String twoObjectId;
    private double twoObjectAcc;
    private String xList;
    private String yList;
    private double xBox;
    private double yBox;
    private double width;
    private double height;
    private TwoSegmentationImage twoSegmentationId;
    
}
