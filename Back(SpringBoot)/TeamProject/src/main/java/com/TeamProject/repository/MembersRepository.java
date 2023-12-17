package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TeamProject.Domain.Members;

public interface MembersRepository extends JpaRepository<Members, Integer>{

    String findNameByLoginEmail(String logingEmail);
    Members findByLoginEmail(String loginEmail);
}
