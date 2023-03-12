package com.reccos.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Team;
import com.reccos.admin.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;

	public Team listById(Long id) {
		Optional<Team> obj = teamRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! TEAM ID " + id));
	}
	
	public List<Team> listByStatus(boolean status) {
		List<Team> obj = teamRepository.findByStatus(status);
		return obj;
	}
	
	public List<Team> searchByChar(String letra) {
		System.out.println("BUSCA POR LETRA: " +letra);
		return teamRepository.findByNameStartingWith(letra);
	}
		
	public List<Team> findByName(String name) {
		System.out.println("BUSCA POR LETRA: " +name);
		return teamRepository.findByNameContaining(name);
	}

	public List<Team> listAll() {
		return teamRepository.findAll();
	}

	public Team create(Team time) {
		return teamRepository.save(time);
	}

	public Team update(Long id, Team time) {
		time.setId(id);
		return teamRepository.save(time);
	}

	public void delete(Long id) {
		teamRepository.deleteById(id);
	}
}
