package com.TeamProject.Controller.Interface;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;

import com.TeamProject.Domain.HistoryView;

public interface HistoryTableInterface {

    Map<Object, Object> historyUpdateDate(Authentication authentication);

    HashMap<String, HistoryView> historyList(@PathVariable LocalDate uploadDate,
                                              Authentication authentication);

    Map<String, Object> historyResults(@PathVariable LocalDate uploadDate,
                                        Authentication authentication);
    
}
