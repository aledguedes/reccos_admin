package com.reccos.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Player;
import com.reccos.admin.repository.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository repository;

	public Player listById(Long id) {
		Optional<Player> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! PLAYER ID " + id));
	}

	public List<Player> listAll() {
		return repository.findAll();
	}
	
	public Page<Player> listAllPaginate(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public List<Player> searchByChar(String letra) {
		return repository.findByNameStartingWith(letra);
	}

	public List<Player> findByName(String name) {
		return repository.findByNameContaining(name);
	}

	public List<Player> listByStatus(boolean status) {
		List<Player> obj = repository.findByStatus(status);
		return obj;
	}

	public Player create(Player obj) {
		obj.setId(null); 

		System.out.println("DEBUG OBJ: " + obj.getId());
		return repository.save(obj);
	}

	public Player update(Long id, Player obj) {
		obj.setId(id);
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
