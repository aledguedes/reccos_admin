package com.reccos.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Goal;
import com.reccos.admin.repository.GoalRepository;

@Service
public class GoalService {

	
	@Autowired
	private GoalRepository repository;
	
	public Goal listById(Long id) {
		Optional<Goal> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! GOAL ID " + id));
	}

	public List<Goal> listAll() {
		return repository.findAll();
	}

	public Goal create(Goal obj) {
//		obj.setId(null);
		return repository.save(obj);
	}

	public Goal update(Long id, Goal obj) {
		obj.setId(id);
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
