package com.reccos.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Stadium;
import com.reccos.admin.repository.StadiumRepository;

@Service
public class StadiumService {

	@Autowired
	private StadiumRepository repository;

	public Stadium listById(Long id) {
		Optional<Stadium> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! REFREE ID " + id));
	}
	
	public List<Stadium> stadiumByTeamId(Long team_id) {
		return repository.findStadiumByTeamsId(team_id);
	}

	public List<Stadium> listAll() {
		return repository.findAll();
	}

	public Stadium create(Stadium obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Stadium update(Long id, Stadium obj) {
		obj.setId(id);
		Stadium s = listById(id);
		System.out.println("estádio: "+s.getName());
		obj.setImg_stadium(s.getImg_stadium());
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
