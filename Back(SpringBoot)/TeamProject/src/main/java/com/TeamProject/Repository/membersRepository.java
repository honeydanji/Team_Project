package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TeamProject.Domain.members;

public interface membersRepository extends JpaRepository<members, Integer>{

    members findByloginEmail(String logingEmail);

    @Query("SELECT m.name FROM members m WHERE loginEmail = ?1")
    String nameByLoginEmail(String logingEmail);

    members findByLoginEmail(String loginEmail);
}
