package com.TeamProject.Controller.SpringBootController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.TeamProject.Service.SpringBootService.historyTableService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class historyTableController {

    private final historyTableService historytableservice;
    
    @GetMapping("/history")
    public HashMap<Integer, LocalDate> historyController(Authentication authentication) {
        return historytableservice.historyUpdateDate(authentication);
    }

    @GetMapping("/history/{historyId}")
    public ResponseEntity<List<Object>> historyList(@PathVariable Integer historyId,
                                    //@PathVariable LocalDate uploadDate,
                                    Authentication authentication) {
                                                
        return historytableservice.detail(historyId, authentication);
    }
}
