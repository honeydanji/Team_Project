package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TeamProject.Domain.twoSegmentationImage;

public interface twoSegmentationRepository extends JpaRepository<twoSegmentationImage, Integer> {
    
}
