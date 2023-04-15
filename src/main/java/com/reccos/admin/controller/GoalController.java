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

import com.reccos.admin.model.Goal;
import com.reccos.admin.service.GoalService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/goals")
public class GoalController {
	
	@Autowired
	private GoalService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Goal> listById(@PathVariable Long id) {
		Goal goal = service.listById(id);
		return ResponseEntity.ok().body(goal);
	}
	
	@GetMapping(value = "/{match_id}/match")
	public ResponseEntity<List<Goal>> listGoalsByMatchId(@PathVariable Long match_id) {
		List<Goal> goal = service.goalByMatch(match_id);
		return ResponseEntity.ok().body(goal);
	}
	
	@GetMapping
	public ResponseEntity<List<Goal>> listarTodos(){
		List<Goal> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Goal> criarGoal(@RequestBody Goal goal) {
		Goal obj = service.create(goal);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Goal> atualizarGoal(@PathVariable Long id, @RequestBody Goal goal){
		Goal obj = service.update(id, goal);
		
		if(obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}

}
