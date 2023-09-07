package com.TeamProject.Domain;

import jakarta.persistence.Column;
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
public class threePointCloudCoordinates {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer threeCoordinatesId;

    private String threeObjectId;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String xList;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String yList;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String zList;

    @OneToOne
    @JoinColumn(name="three_original_id")
    private threeOriginalPointCloud threeOriginalId;

}
