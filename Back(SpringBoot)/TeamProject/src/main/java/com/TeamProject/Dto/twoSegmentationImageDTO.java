package com.TeamProject.Dto;

import com.TeamProject.Domain.HistoryTable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TwoSegmentationImageDTO {

    private Integer twoSegmentationId;
    private String twoSegmentationPath;
    private HistoryTable historyId;
}
