package com.TeamProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TeamProject.Domain.members;

public interface membersRepository extends JpaRepository<members, Integer>{

    members findByloginEmail(String logingEmail);

}
