package com.reccos.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Card;
import com.reccos.admin.model.Match;
import com.reccos.admin.model.Player;
import com.reccos.admin.model.Suspend;
import com.reccos.admin.repository.CardRepository;

@Service
public class CardService {

	@Autowired
	private CardRepository repository;

	@Autowired
	private MatchService matchService;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private SuspendService suspendService;

	public Card listById(Long id) {
		Optional<Card> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! ID " + id));
	}

	public List<Card> cardByPlayerId(long id) {
		List<Card> c = repository.findCradByPlayerId(id);
		return c;
	}

	public List<Card> listAll() {
		return repository.findAll();
	}

	public Card create(Card obj) {
		Integer suspenso = 0;
		Match m = matchService.listById(obj.getMatch().getId());
		Player p = playerService.listById(obj.getPlayer().getId());
		System.out.println("DEBUG R_CARD: "+obj.getRed_card());
		System.out.println("DEBUG Y_CARD: "+obj.getYellow_card());
		obj.setMatch(m);
		obj.setPlayer(p);

		repository.save(obj);
		
		List<Card> newCard = repository.findCradByPlayerId(obj.getPlayer().getId());

		for (Card num_card : newCard) {
			if (obj.getRed_card() == 1) {
				p.setSuspend(true);
			}
			suspenso = suspenso + num_card.getYellow_card();
			if (suspenso % 3 == 0) {
				p.setSuspend(true);
			}
		}

		Suspend s = suspendService.create((long) 1 ,p.getId());
		playerService.update(p.getId(), p);
		return obj;
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

	public List<Card> cardByMatch(Long match_id) {
		return repository.findCardsByMatchId(match_id);
	}
}
