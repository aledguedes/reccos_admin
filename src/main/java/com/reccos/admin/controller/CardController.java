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
		Card arbitro = service.listById(id);
		return ResponseEntity.ok().body(arbitro);
	}
	
	@GetMapping
	public ResponseEntity<List<Card>> listarTodos(){
		List<Card> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Card> criarCard(@RequestBody Card arbitro) {
		Card obj = service.create(arbitro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Card> atualizarCard(@PathVariable Long id, @RequestBody Card arbitro){
		Card obj = service.update(id, arbitro);
		
		if(obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}
}
