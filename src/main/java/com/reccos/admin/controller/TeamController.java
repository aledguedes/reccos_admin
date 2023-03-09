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

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Federation;
import com.reccos.admin.model.Team;
import com.reccos.admin.repository.FederationRepository;
import com.reccos.admin.repository.TeamRepository;
import com.reccos.admin.service.TeamService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class TeamController {

	@Autowired
	private TeamService service;

	@Autowired
	private FederationRepository tutorialRepository;

	@Autowired
	private TeamRepository teamRepository;

	@GetMapping("/teams")
	public ResponseEntity<List<Team>> listarTodos() {
		List<Team> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/federation/{ligaId}/teams")
	public ResponseEntity<List<Team>> getAllTagsByTutorialId(@PathVariable(value = "ligaId") Long ligaId) {
		if (!tutorialRepository.existsById(ligaId)) {
			throw new ObjectnotFoundException("Not found Tutorial with id = " + ligaId);
		}

		List<Team> tags = teamRepository.findTeamsById(ligaId);
		return new ResponseEntity<>(tags, HttpStatus.OK);
	}

	@GetMapping("/teams/{id}")
	public ResponseEntity<Team> listById(@PathVariable Long id) {
		Team time = service.listById(id);
		return ResponseEntity.ok().body(time);
	}
	
	@GetMapping("/teams/find/character")
	public ResponseEntity<List<Team>> searchByChar(@RequestParam (name="key") String key) {
		System.out.println("key = " + key);
		return ResponseEntity.ok(service.searchByChar(key));
	}
	
	@GetMapping("/teams/find/name")
	public ResponseEntity<List<Team>> findByName(@RequestParam (name="name") String name) {
		System.out.println("key = " + name);
		return ResponseEntity.ok(service.findByName(name));
	}

	@GetMapping("/teams/{teamId}/federation")
	public ResponseEntity<List<Federation>> getAllTutorialsByTagId(@PathVariable(value = "teamId") Long teamId) {
		if (!teamRepository.existsById(teamId)) {
			throw new ObjectnotFoundException("Not found Tag  with id = " + teamId);
		}

		List<Federation> tutorials = tutorialRepository.findFederationByTeamsId(teamId);
		return new ResponseEntity<>(tutorials, HttpStatus.OK);
	}
	
	@PostMapping("/teams")
	public ResponseEntity<Team> criarTime(@RequestBody Team time) {
		Team obj = service.create(time);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PostMapping("/federation/{ligaId}/teams")
	public ResponseEntity<Team> addTag(@PathVariable(value = "ligaId") Long ligaId,
			@RequestBody Team tagRequest) {
		Team tag = tutorialRepository.findById(ligaId).map(tutorial -> {
			long teamId = tagRequest.getId();

			// tag is existed
			if (teamId != 0L) {
				Team _tag = teamRepository.findById(teamId)
						.orElseThrow(() -> new ObjectnotFoundException("Not found Tag with id = " + teamId));
				tutorial.addTag(_tag);
				tutorialRepository.save(tutorial);
				return _tag;
			}

			// add and create new Tag
			tutorial.addTag(tagRequest);
			return teamRepository.save(tagRequest);
		}).orElseThrow(() -> new ObjectnotFoundException("Not found Tutorial with id = " + ligaId));

		return new ResponseEntity<>(tag, HttpStatus.CREATED);
	}

	@PutMapping("/teams/{id}")
	public ResponseEntity<Team> updateTag(@PathVariable("id") long id, @RequestBody Team time) {
		Team obj = service.update(id, time);

		if (obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/federation/{ligaId}/teams/{teamId}")
	public ResponseEntity<HttpStatus> deleteTagFromTutorial(@PathVariable(value = "ligaId") Long ligaId,
			@PathVariable(value = "teamId") Long teamId) {
		Federation tutorial = tutorialRepository.findById(ligaId)
				.orElseThrow(() -> new ObjectnotFoundException("Not found Tutorial with id = " + ligaId));

		tutorial.removeTag(teamId);
		tutorialRepository.save(tutorial);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/teams/{id}")
	public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") long id) {
		teamRepository.deleteById(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
