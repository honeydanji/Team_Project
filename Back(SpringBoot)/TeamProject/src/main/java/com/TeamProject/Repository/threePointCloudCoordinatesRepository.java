package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TeamProject.Domain.ThreePointCloudCoordinates;

public interface ThreePointCloudCoordinatesRepository extends JpaRepository<ThreePointCloudCoordinates, Integer>{
    
}
