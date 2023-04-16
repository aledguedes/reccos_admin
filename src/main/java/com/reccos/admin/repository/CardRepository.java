package com.reccos.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

	List<Card> findCradByPlayerId(long id);

	List<Card> findCardsByMatchId(Long match_id);

}
