package com.reccos.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Card;
import com.reccos.admin.model.Match;
import com.reccos.admin.model.Player;
import com.reccos.admin.repository.CardRepository;

@Service
public class CardService {

	@Autowired
	private CardRepository repository;
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private PlayerService playerService;
	
	public Card listById(Long id) {
		Optional<Card> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! ID " + id));
	}

	public List<Card> listAll() {
		return repository.findAll();
	}

	public Card create(Card obj) {
		Match m = matchService.listById(obj.getMatch().getId());
		Player p = playerService.listById(obj.getPlayer().getId());
		obj.setMatch(m);
		obj.setPlayer(p);
		return repository.save(obj);
	}

	public Card update(Long id, Card obj) {
		obj.setId(id);
		Match m = matchService.listById(obj.getMatch().getId());
		Player p = playerService.listById(obj.getPlayer().getId());
		obj.setMatch(m);
		obj.setPlayer(p);
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
