package com.TeamProject.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TwoSegmentationImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer twoSegmentationId;

    private String twoSegmentationPath;

    @OneToOne
    @JoinColumn(name="history_id", nullable = false)
    private HistoryTable historyId;
}
