package com.TeamProject.Domain;

import java.sql.Time;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Immutable
public class historyView {
    
    @Id
    private Integer historyId;

    private Time uploadTime;
    private String twoOriginalPath;
    private String twoSegmentationPath;
    private String comment;
}
