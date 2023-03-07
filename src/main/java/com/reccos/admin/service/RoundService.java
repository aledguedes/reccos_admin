package com.reccos.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Match;
import com.reccos.admin.model.Round;
import com.reccos.admin.repository.RoundRepository;

@Service
public class RoundService {

	@Autowired
	private RoundRepository repository;

	@Autowired
	private MatchService mService;

	public Round listById(Long id) {
		Optional<Round> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! ID " + id));
	}

	public List<Round> listAll() {
		return repository.findAll();
	}

	public Round create(Round obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Round update(Long id, Round obj) {
		obj.setId(id);
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Round createRound(Round obj) {
		Round newRound = new Round();
		newRound.setId(obj.getId());
		newRound.getMatches().addAll(obj.getMatches().stream().map(v -> {
			Match mm = mService.listById(v.getId());
			mm.getRounds().add(newRound);
			return mm;
		}).collect(Collectors.toList()));
		
		return repository.save(newRound);
	}
}
