package com.TeamProject.Controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.TeamProject.Controller.Interface.HistoryTableInterface;
import com.TeamProject.Domain.HistoryView;
import com.TeamProject.Service.SpringBootService.HistoryTableService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HistoryTableController implements HistoryTableInterface{

    private final HistoryTableService historytableservice;
    
    // historyDate
    @GetMapping("/history")
    public Map<Object, Object> historyUpdateDate(Authentication authentication) {
        return historytableservice.historyUpdateDate(authentication);
    }

    // historyData
    @GetMapping("/history/{uploadDate}")
    public HashMap<String, HistoryView> historyList(@PathVariable LocalDate uploadDate,
                                                    Authentication authentication) {                           
        return historytableservice.detail(uploadDate, authentication);
    }

    // results
    @GetMapping("/results/{uploadDate}")
    public Map<String, Object> historyResults(@PathVariable LocalDate uploadDate,
                                                    Authentication authentication) {
        return historytableservice.results(uploadDate, authentication);
    }
}
