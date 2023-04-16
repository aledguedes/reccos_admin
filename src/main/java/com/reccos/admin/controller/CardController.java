package com.reccos.admin.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reccos.admin.model.Card;
import com.reccos.admin.service.CardService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cards")
public class CardController {

	@Autowired
	private CardService service;
	
	@GetMapping(value = "/{id}")	
	public ResponseEntity<Card> listById(@PathVariable Long id) {
		Card card = service.listById(id);
		return ResponseEntity.ok().body(card);
	}
	
	@GetMapping(value = "/{match_id}/match")
	public ResponseEntity<List<Card>> listGoalsByMatchId(@PathVariable Long match_id) {
		List<Card> card = service.cardByMatch(match_id);
		return ResponseEntity.ok().body(card);
	}
	
	@GetMapping
	public ResponseEntity<List<Card>> listarTodos(){
		List<Card> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}
	
//	@GetMapping("/players/{id_player}")
//	public ResponseEntity<List<Card>> playerIdWithCard(@PathVariable Long id_player) {
//		List<Card> list = service.cardByPlayerId(id_player);
//		return ResponseEntity.ok().body(list);
//	}
	
	@PostMapping
	public ResponseEntity<Card> criarCard(@RequestBody Card card) {
		Card obj = service.create(card);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Card> atualizarCard(@PathVariable Long id, @RequestBody Card card){
		Card obj = service.update(id, card);
		
		if(obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}
}
