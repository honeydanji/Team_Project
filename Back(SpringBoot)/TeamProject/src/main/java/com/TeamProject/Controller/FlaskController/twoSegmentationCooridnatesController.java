package com.TeamProject.Controller.FlaskController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TeamProject.Service.SpringBootService.twoSegmentationCoordinatesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class twoSegmentationCooridnatesController {

    private final twoSegmentationCoordinatesService twosegmentationcoordinatesservice;

    @GetMapping("/segResponse/{date}")
    public List<Object[]> segResponse() {
        return twosegmentationcoordinatesservice.segResponseAll();
    }
    
}
