package com.TeamProject.Domain;

import java.sql.Time;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Integer historyId;

    private LocalDate uploadDate;
    private Time uploadTime;

    
    @ManyToOne
    @JoinColumn(name="user_id")
    private members userId;
}
