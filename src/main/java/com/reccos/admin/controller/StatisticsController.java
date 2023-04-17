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

import com.reccos.admin.model.Statistics;
import com.reccos.admin.service.StatisticsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

	@Autowired
	private StatisticsService service;
	
	@GetMapping("/{id}")	
	public ResponseEntity<Statistics> listById(@PathVariable Long id) {
		Statistics statistics = service.listById(id);
		return ResponseEntity.ok().body(statistics);
	}
	
	@GetMapping("/{id_team}/teams")	
	public ResponseEntity<Statistics> listByTeamId(@PathVariable Long id_team) {
		Statistics statistics = service.statisticsByTeamId(id_team);
		return ResponseEntity.ok().body(statistics);
	}
	
	@GetMapping
	public ResponseEntity<List<Statistics>> listarTodos(){
		List<Statistics> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Statistics> criarStatistics(@RequestBody Statistics statistics) {
		Statistics obj = service.create(statistics);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Statistics> atualizarStatistics(@PathVariable Long id, @RequestBody Statistics statistics){
		Statistics obj = service.update(id, statistics);
		
		if(obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Statistics> deletarStatistics(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
