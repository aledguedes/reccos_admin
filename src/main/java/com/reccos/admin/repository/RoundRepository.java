package com.reccos.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.Round;

public interface RoundRepository extends JpaRepository<Round, Long> {

	Page<Round> findRoundByLeagueId(long id, Pageable paging);

	Page<Round> findRoundBynumRound(long num_round, Pageable paging);

}
