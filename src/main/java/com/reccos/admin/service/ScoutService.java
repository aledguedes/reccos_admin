package com.reccos.admin.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Scout;
import com.reccos.admin.repository.ScoutRepository;


@Service
public class ScoutService {
	@Autowired
	private ScoutRepository repository;

	public Scout listById(Long id) {
		Optional<Scout> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! FEDERATION ID " + id));
	}

	public List<Scout> listAll() {
		return repository.findAll();
	}

	public Scout create(Scout obj) {
		return repository.save(obj);
	}

	public Scout update(Long id, Scout obj) {
		obj.setId(id);
		return repository.save(obj);
	}
}
