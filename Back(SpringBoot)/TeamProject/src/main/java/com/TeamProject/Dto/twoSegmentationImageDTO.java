package com.TeamProject.Dto;

import com.TeamProject.Domain.historyTable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class twoSegmentationImageDTO {

    private Integer twoSegmentationId;
    private String twoSegmentationPath;
    private historyTable historyId;
}
