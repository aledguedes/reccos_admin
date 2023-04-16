package com.reccos.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.Stadium;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
	List<Stadium> findStadiumByTeamsId(Long team_id);
}
