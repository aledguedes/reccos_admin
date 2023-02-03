package com.reccos.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Refree;
import com.reccos.admin.repository.RefreeRepository;

@Service
public class RefreeService {

	@Autowired
	private RefreeRepository repository;
	
	public Refree listById(Long id) {
		Optional<Refree> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! ID " + id));
	}
	
	public List<Refree> listAll() {
		return repository.findAll();
	}
	
	public List<Refree> listByStatus(boolean status) {
		List<Refree> obj = repository.findByStatus(status);
		return obj;
	}
	
	public Refree create(Refree obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Refree update(Long id, Refree obj) {
		obj.setId(id);
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
