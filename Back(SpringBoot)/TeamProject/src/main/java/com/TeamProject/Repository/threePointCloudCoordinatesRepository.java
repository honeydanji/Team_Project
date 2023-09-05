package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TeamProject.Domain.threePointCloudCoordinates;

public interface threePointCloudCoordinatesRepository extends JpaRepository<threePointCloudCoordinates, Integer>{
    
}
