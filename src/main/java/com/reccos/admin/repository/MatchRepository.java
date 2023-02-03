package com.reccos.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {

}
