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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="2d_original_image")
public class twoOriginalImage {
    
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="2d_original_id")
    private Integer twoOriginalId;

    @Column(name="2d_original_path")
    private String twoOriginalPath;

    @OneToOne
    @JoinColumn(name="history_id")
    private historyTable history_id;
}
