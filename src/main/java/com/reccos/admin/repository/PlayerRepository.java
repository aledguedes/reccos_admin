package com.reccos.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

	List<Player> findByStatus(boolean status);
	List<Player> findByNameStartingWith(String letra);
	List<Player> findByNameContaining(String name);
}