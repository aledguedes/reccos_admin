package com.reccos.admin.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reccos.admin.model.Match;
import com.reccos.admin.service.MatchService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class MatchController {

	@Autowired
	private MatchService service;

	@GetMapping("/matches")
	public ResponseEntity<List<Match>> getAllTutorials(@RequestParam(required = false) String title) {
		List<Match> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/matches/{id}")
	public ResponseEntity<Match> getTutorialById(@PathVariable("id") long id) {
		Match league = service.listById(id);
		return ResponseEntity.ok().body(league);
	}

	@PostMapping("/matches")
	public ResponseEntity<List<Match>> createRound(@RequestBody List<Match> match) {

		List<Match> obj = service.create(match);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj).toUri();
		return ResponseEntity.created(uri).build();
	}
}
