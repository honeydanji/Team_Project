package com.TeamProject.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PoseDataDTO {

    private String objectId;
    private double x;
    private double y;
    private double z;
    private double rx;
    private double ry;
    private double rz;
    // private historyTable historyId;
    
}
