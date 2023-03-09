package com.reccos.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.League;

public interface LeagueRepository extends JpaRepository<League, Long> {
	List<League> findByStatus(boolean status);

	List<League> findByNameContaining(String name);
}