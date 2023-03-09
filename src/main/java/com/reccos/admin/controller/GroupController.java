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

import com.reccos.admin.model.Group;
import com.reccos.admin.service.GroupService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/groups")
public class GroupController {

	@Autowired
	private GroupService service;
	
	@GetMapping(value = "/{id}")	
	public ResponseEntity<Group> listById(@PathVariable Long id) {
		Group arbitro = service.listById(id);
		return ResponseEntity.ok().body(arbitro);
	}
	
	@GetMapping
	public ResponseEntity<List<Group>> listarTodos(){
		List<Group> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Group> criarGroup(@RequestBody Group arbitro) {
		Group obj = service.create(arbitro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Group> atualizarGroup(@PathVariable Long id, @RequestBody Group arbitro){
		Group obj = service.update(id, arbitro);
		
		if(obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}
}
