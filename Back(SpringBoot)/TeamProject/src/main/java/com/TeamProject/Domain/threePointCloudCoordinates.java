package com.TeamProject.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="3d_point_cloud_coordinates")
public class threePointCloudCoordinates {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="3d_coordinates_id")
    private Integer threeCoordinatesId;

    @Column(name="3d_object_id")
    private String threeObjectId;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String xList;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String yList;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String zList;

    @OneToOne
    @JoinColumn(name="3d_original_id")
    private threeOriginalPointCloud threeOriginalId;

}
