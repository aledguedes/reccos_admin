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

import com.reccos.admin.model.Round;
import com.reccos.admin.service.RoundService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class RoundController {

	@Autowired
	private RoundService service;

	@GetMapping("/rounds")
	public ResponseEntity<List<Round>> getAllTutorials(@RequestParam(required = false) String title) {
		List<Round> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/rounds/{id}")
	public ResponseEntity<Round> getTutorialById(@PathVariable("id") long id) {
		Round obj = service.listById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping("/rounds")
	public ResponseEntity<Round> createRound(@RequestBody Round organization) {
		System.out.println("que vem? " + organization);
		Round obj = service.createRound(organization);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
//		return new ResponseEntity<>(service.create(organization), HttpStatus.CREATED);
	}
}
