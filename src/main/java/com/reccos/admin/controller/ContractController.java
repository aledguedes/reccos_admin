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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reccos.admin.model.Contract;
import com.reccos.admin.service.ContractService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ContractController {
	@Autowired
	private ContractService service;
	
	@GetMapping("/contracts")
	public ResponseEntity<List<Contract>> getAllTutorials(@RequestParam(required = false) String title) {
		List<Contract> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/contracts/{id}")
	public ResponseEntity<Contract> getTutorialById(@PathVariable("id") long id) {
		Contract obj = service.listById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping("/contracts")
	public ResponseEntity<Contract> createContract(@RequestBody Contract contract) {
		Contract obj = service.create(contract);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/contracts/{id}")
	public ResponseEntity<Contract> updateTag(@PathVariable("id") long id, @RequestBody Contract contract) {
		Contract obj = service.update(id, contract);

		if (obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}

}
