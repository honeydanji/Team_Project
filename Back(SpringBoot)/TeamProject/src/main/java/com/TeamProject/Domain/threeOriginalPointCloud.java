package com.TeamProject.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ThreeOriginalPointCloud {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer threeOriginalId;

    private String threeOriginalPath;

    @OneToOne
    @JoinColumn(name="history_id")
    private HistoryTable historyId;
    
}
