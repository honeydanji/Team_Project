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
@Table(name="3d_original_point_cloud")
public class threeOriginalPointCloud {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="3d_original_id")
    private Integer threeOriginalId;

    @Column(name="3d_original_path")
    private String threeOriginalPath;

    @OneToOne
    @JoinColumn(name="history_id")
    private historyTable historyId;
    
}
