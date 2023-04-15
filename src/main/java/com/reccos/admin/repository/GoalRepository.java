package com.reccos.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {
	List<Goal> findGoalsByMatchId(Long match_id);
}
