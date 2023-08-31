package com.TeamProject.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="2d_segmentation_coordinates")
public class twoSegmentationCoordinates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="2d_coordinate_id")
    private Integer twoCoordinateId;

    @Column(name="2d_object_id")
    private int twoObjectId;

    @Column(name="2d_object_acc")
    private float twoObjectAcc;

    private String xList;

    private String yList;

    @OneToOne
    @Column(name="2d_segmentation_id")
    private twoSegmentationImage twoSegmentationId;
    
}
