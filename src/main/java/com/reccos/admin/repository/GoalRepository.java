package com.reccos.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {

}
