package com.reccos.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Suspend;
import com.reccos.admin.repository.SuspendRepository;

@Service
public class SuspendService {

	@Autowired
	private SuspendRepository repository;

	public Suspend listById(Long id) {
		Optional<Suspend> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! REFREE ID " + id));
	}

	public List<Suspend> listAll() {
		return repository.findAll();
	}

//	public List<Suspend> listByStatus(boolean status) {
//		List<Suspend> obj = repository.findByStatus(status);
//		return obj;
//	}

	public Suspend create(Long idd_round, Long idd_player) {
		System.out.println("ESTAMOS AQUI");
		Suspend s = new Suspend();
		s.setIdd_round(idd_round);
		s.setIdd_player(idd_player);
		s.setStatus_suspend("A cumprir");
		return repository.save(s);
	}

	public Suspend update(Long id, Suspend obj) {
		obj.setId(id);
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
