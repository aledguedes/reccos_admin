package com.reccos.admin.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reccos.admin.model.Stadium;
import com.reccos.admin.service.StadiumService;

@RestController
@RequestMapping(value = "/api/stadium")
@CrossOrigin("*")
public class StadiumController {

	@Autowired
	private StadiumService service;
	
	@GetMapping(value = "/{id}")	
	public ResponseEntity<Stadium> listById(@PathVariable Long id) {
		Stadium stadium = service.listById(id);
		return ResponseEntity.ok().body(stadium);
	}
	
	@GetMapping(value = "/{team_id}/teams")
	public ResponseEntity<List<Stadium>> stadiumByTeamId(@PathVariable Long team_id) {
		List<Stadium> stadium = service.stadiumByTeamId(team_id);
		return ResponseEntity.ok().body(stadium);
	}
	
	@GetMapping
	public ResponseEntity<List<Stadium>> listarTodos(){
		List<Stadium> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Stadium> criarStadium(@RequestBody Stadium stadium) {
		Stadium obj = service.create(stadium);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Stadium> atualizarStadium(@PathVariable Long id, @RequestBody Stadium stadium){
		Stadium obj = service.update(id, stadium);
		
		if(obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Stadium> deletarStadium(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
