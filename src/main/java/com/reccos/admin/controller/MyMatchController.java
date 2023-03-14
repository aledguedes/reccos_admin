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

import com.reccos.admin.model.MyMatch;
import com.reccos.admin.service.MyMatchService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/mymatch")

public class MyMatchController {

	@Autowired
	private MyMatchService service;
	
	@GetMapping
	public ResponseEntity<List<MyMatch>> getAllMyMatches() {
		List<MyMatch> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MyMatch> getTutorialById(@PathVariable("id") long id) {
		MyMatch obj = service.listById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<MyMatch> createMyMatch(@RequestBody MyMatch match) {
		MyMatch obj = service.create(match);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MyMatch> updateTag(@PathVariable("id") long id, @RequestBody MyMatch match) {
		MyMatch obj = service.update(id, match);

		if (obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}
}
