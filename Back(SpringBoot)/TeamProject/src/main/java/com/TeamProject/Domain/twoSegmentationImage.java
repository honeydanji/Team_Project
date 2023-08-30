package com.TeamProject.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="2d_segmentation_image")
public class twoSegmentationImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="2d_segmentation_id")
    private Integer twoSegmentationId;

    @Column(name="2d_process_path")
    private String twoSegmentationPath;

    @OneToOne
    @JoinColumn(name="history_id", nullable = false)
    private historyTable history_id;
}
