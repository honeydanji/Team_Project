package com.TeamProject.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class poseData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer poseId;

    private String objectId;
    private double x;
    private double y;
    private double z;
    private double rx;
    private double ry;
    private double rz;
    
    @ManyToOne
    @JoinColumn(name="history_id")
    private historyTable historyId;
}
