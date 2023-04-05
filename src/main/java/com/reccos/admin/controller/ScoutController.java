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

import com.reccos.admin.model.Scout;
import com.reccos.admin.service.ScoutService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/scouts")
public class ScoutController {
	
	@Autowired
	private ScoutService service;
	
	@GetMapping(value = "/{id}")	
	public ResponseEntity<Scout> listById(@PathVariable Long id) {
		Scout federation = service.listById(id);
		return ResponseEntity.ok().body(federation);
	}
	
	@GetMapping
	public ResponseEntity<List<Scout>> listarTodos(){
		List<Scout> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Scout> criarScout(@RequestBody Scout federation) {
		Scout obj = service.create(federation);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Scout> atualizarScout(@PathVariable Long id, @RequestBody Scout federation){
		Scout obj = service.update(id, federation);
		
		if(obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}

}
