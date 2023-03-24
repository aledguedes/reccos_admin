package com.reccos.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Match;
import com.reccos.admin.repository.MatchRepository;

@Service
public class MatchService {

	@Autowired
	private MatchRepository repository;
	
	public Match listById(Long id) {
		Optional<Match> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! MATCH ID " + id));
	}

	public List<Match> listAll() {
		return repository.findAll();
	}

	public Page<Match> allMatchesPage(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		return repository.findAll(paging);
	}

	public Match createNow(Match obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Match update(Long id, Match obj) {
		obj.setId(id);
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public List<Match> create(List<Match> match) {
		List<Match> matches = new ArrayList<>();

	    for (Match m : match) {
	    	matches.add(m);
	    }

	    Iterable<Match> persistedUser = repository.saveAll(matches);
		return repository.saveAll(persistedUser);
	}

	public List<Match> matchIdTeam(long id, int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		return repository.findMatchByHomeId(id, paging);
	}
}
