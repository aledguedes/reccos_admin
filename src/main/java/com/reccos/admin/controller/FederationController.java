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

import com.reccos.admin.model.Federation;
import com.reccos.admin.service.FederationService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/federation")
public class FederationController {

	@Autowired
	private FederationService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Federation> listById(@PathVariable Long id) {
		Federation federation = service.listById(id);
		return ResponseEntity.ok().body(federation);
	}
	
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<List<Federation>> listByStatus(@PathVariable Boolean status) {
		List<Federation> list = service.listByStatus(status);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping
	public ResponseEntity<List<Federation>> listarTodos(){
		List<Federation> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Federation> criarFederation(@RequestBody Federation federation) {
		Federation obj = service.create(federation);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Federation> atualizarFederation(@PathVariable Long id, @RequestBody Federation federation){
		Federation obj = service.update(id, federation);
		
		if(obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}
}
