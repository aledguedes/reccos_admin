package com.reccos.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Card;
import com.reccos.admin.repository.CardRepository;

@Service
public class CardService {

	@Autowired
	private CardRepository repository;
	
	public Card listById(Long id) {
		Optional<Card> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! ID " + id));
	}

	public List<Card> listAll() {
		return repository.findAll();
	}

	public Card create(Card obj) {
//		obj.setId(null);
		return repository.save(obj);
	}

	public Card update(Long id, Card obj) {
		obj.setId(id);
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
