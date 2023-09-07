package com.TeamProject.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class twoSegmentationCoordinates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer twoCoordinateId;

    private String twoObjectId;

    private double twoObjectAcc;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String xList;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String yList;

    private double xBox;
    private double yBox;
    private double width;
    private double height;
    
    @ManyToOne
    @JoinColumn(name="two_segmentation_id", nullable = false)
    private twoSegmentationImage twoSegmentationId;
    
}
