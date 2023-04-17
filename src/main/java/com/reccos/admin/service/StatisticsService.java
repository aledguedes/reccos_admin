package com.reccos.admin.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Statistics;
import com.reccos.admin.repository.StatisticsRepository;

@Service
public class StatisticsService {

	@Autowired
	private StatisticsRepository repository;

	public Statistics listById(Long id) {
		Optional<Statistics> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! REFREE ID " + id));
	}

	public List<Statistics> listAll() {
		return repository.findAll();
	}
	
	public Statistics statisticsByTeamId(long id_team) {
		return repository.findStatisticsByTeamId(id_team);
	}

	public Statistics create(Statistics obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Statistics update(Long id, Statistics obj) {
		obj.setId(id);
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
