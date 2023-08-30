package com.TeamProject.Domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class historyTable {
    
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer history_id;

    private LocalDate uploadDate;

    private String userId;
}
