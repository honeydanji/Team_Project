package com.TeamProject.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
public class twoOriginalImage {
    
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer twoOriginalId;

    private String twoOriginalPath;

    @OneToOne
    @JoinColumn(name="history_id", nullable=false)
    private historyTable historyId;
}
