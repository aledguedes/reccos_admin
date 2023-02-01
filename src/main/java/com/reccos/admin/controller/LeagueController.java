package com.reccos.admin.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reccos.admin.model.League;
import com.reccos.admin.repository.LeagueRepository;
import com.reccos.admin.service.LeagueService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class LeagueController {

	@Autowired
	private LeagueRepository tutorialRepository;

	@Autowired
	private LeagueService lService;

	@GetMapping("/leagues")
	public ResponseEntity<List<League>> getAllTutorials(@RequestParam(required = false) String title) {
		List<League> list = lService.listAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/free")
	public String sayFreeHello() {
		return "ENDPOINT LIBERADO PARA ACESSO SEM TOKEN";
	}
	
	@GetMapping("/auth")
	public String sayAuthHello() {
		return "ENDPOINT NÃO LIBERADO PARA ACESSO SEM TOKEN";
	}

	@GetMapping("/leagues/{id}")
	public ResponseEntity<League> getTutorialById(@PathVariable("id") long id) {
		League league = lService.listById(id);
		return ResponseEntity.ok().body(league);
	}
	
	@GetMapping("/leagues/find")
	public ResponseEntity<List<League>> searchByChar(@RequestParam (name="name") String key) {
		System.out.println("key = " + key);
		return ResponseEntity.ok(lService.findByName(key));
	}

	@PostMapping("/leagues")
	public ResponseEntity<League> createTutorial(@RequestBody League league) {
		League obj = lService.create(league);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping("/leagues/new")
    public ResponseEntity<League> saveLeague(@RequestBody League league) {
		League obj = lService.saveCustomer(league);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
//        return new ResponseEntity<>(lService.saveCustomer(league), HttpStatus.CREATED);
    }

	@PutMapping("/leagues/{id}")
	public ResponseEntity<League> update(@PathVariable("id") long id, @RequestBody League liga) {
		League obj = lService.update(id, liga);
		
		if(obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/leagues/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		tutorialRepository.deleteById(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/leagues")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		tutorialRepository.deleteAll();

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
