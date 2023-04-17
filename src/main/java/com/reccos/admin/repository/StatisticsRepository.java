package com.reccos.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.Statistics;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

	Statistics findStatisticsByTeamId(long id_team);

}
