package com.reccos.admin.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reccos.admin.model.Round;
import com.reccos.admin.service.RoundService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/rounds")
public class RoundController {

	@Autowired
	private RoundService service;

	@GetMapping("/all")
	public ResponseEntity<List<Round>> getAllTutorials(@RequestParam(required = false) String title) {
		List<Round> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping
	public ResponseEntity<Page<Round>> listAllPagiante(
			@RequestParam (value = "page", required = false, defaultValue = "0") int page,
			@RequestParam (value = "size", required = false, defaultValue = "12") int size){
		Page<Round> list = service.listAllPaginate(page, size);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Round> getTutorialById(@PathVariable("id") long id) {
		Round obj = service.listById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/{id}/leagues")
	public ResponseEntity<Page<Round>> matchByTeamId(
			@PathVariable("id") long id,
			@RequestParam (value = "page", required = false, defaultValue = "0") int page,
			@RequestParam (value = "size", required = false, defaultValue = "12") int size) {
		Page<Round> list = service.roundIdLeague(id, page, size);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/find/{num_round}")
	public ResponseEntity<Page<Round>> matchByNumRound(
			@PathVariable("num_round") long num_round,
			@RequestParam (value = "page", required = false, defaultValue = "0") int page,
			@RequestParam (value = "size", required = false, defaultValue = "12") int size) {
		Page<Round> list = service.roundByNumRound(num_round, page, size); 
		return ResponseEntity.ok().body(list);
	}

	@PostMapping("/{id_league}")
	public ResponseEntity<Round> createRound(@RequestBody Round organization, @PathVariable("id_league") long id_league) {
		Round obj = service.createRound(organization, id_league);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
//		return new ResponseEntity<>(service.create(organization), HttpStatus.CREATED);
	}
	
	//complete round
	@PutMapping("/{id_round}/league/{id_league}")
	public ResponseEntity<Round> finishRound(@PathVariable Long id_round, @PathVariable Long id_league, @RequestBody Round round){
		Round obj = service.finishRound(id_round, id_league, round);
		
		if(obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}
}
