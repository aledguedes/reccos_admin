package com.reccos.admin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {

	List<Match> findMatchByHomeId(long id, Pageable page);

}
