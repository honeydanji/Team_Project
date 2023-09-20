package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.Members;

public interface MembersRepository extends JpaRepository<Members, Integer>{

    Members findByloginEmail(String logingEmail);

    @Query("SELECT m.name FROM members m WHERE loginEmail = ?1")
    String nameByLoginEmail(String logingEmail);

    Members findByLoginEmail(String loginEmail);
}
