package com.reccos.admin.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.reccos.admin.model.Player;
import com.reccos.admin.service.PlayerService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class PlayerController {

	@Autowired
	private PlayerService service;
	
//	@Autowired
//	private UploadService uploadService;
	
	@GetMapping("/players/{id}")
	public ResponseEntity<Player> listById(@PathVariable Long id) {
		Player atleta = service.listById(id);
		return ResponseEntity.ok().body(atleta);
	}
	
	@GetMapping("/players/status/{status}")
	public ResponseEntity<List<Player>> listByStatus(@PathVariable Boolean status) {
		List<Player> list = service.listByStatus(status);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/players/find/teams_char")
	public ResponseEntity<List<Player>> searchByChar(@RequestParam (name="key") String key) {
		return ResponseEntity.ok(service.searchByChar(key));
	}
	
	@GetMapping("/players/find/team_name")
	public ResponseEntity<List<Player>> findByName(@RequestParam (name="name") String name) {
		System.out.println("key = " + name);
		return ResponseEntity.ok(service.findByName(name));
	}

	
	@GetMapping("/players")
	public ResponseEntity<List<Player>> listarTodos(){
		List<Player> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}

	
	@GetMapping("/players/paginate")
	public ResponseEntity<Page<Player>> listarTodos(Pageable pageable){
		Page<Player> list = service.listAllPaginate(pageable);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping("/players")
	public ResponseEntity<Player> criarTime(@RequestBody Player atleta) {
		Player obj = service.create(atleta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
//	@PostMapping("/upload")
//	public ResponseEntity<PathDTO> uploadFoto(@RequestParam(name = "file") MultipartFile file){
//		String path = uploadService.uploadFile(file);
//		if (path != null) {
//			PathDTO pathDto = new PathDTO();
//			pathDto.setPathToFile(path);
//			return ResponseEntity.status(201).body(pathDto);
//		}
//		return ResponseEntity.badRequest().build();
//	}
	
	@PutMapping("/players/{id}")
	public ResponseEntity<Player> atualizarTime(@PathVariable Long id, @RequestBody Player atleta){
		Player obj = service.update(id, atleta);
		
		if(obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/players/{id}")
	public ResponseEntity<Player> deletarTime(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
