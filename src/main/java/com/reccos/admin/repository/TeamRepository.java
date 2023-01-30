package com.reccos.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
	List<Team> findTeamsById(Long tutorialId);
	List<Team> findByStatus(boolean status);
	List<Team> findByNameStartingWith(String letra);
	List<Team> findByNameContaining(String name);
}