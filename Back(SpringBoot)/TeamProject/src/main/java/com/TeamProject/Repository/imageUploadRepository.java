package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TeamProject.Domain.twoOriginalImage;

public interface imageUploadRepository extends JpaRepository<twoOriginalImage, Integer> {
    
}
