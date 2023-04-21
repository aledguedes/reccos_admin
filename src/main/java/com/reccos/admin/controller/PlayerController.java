package com.reccos.admin.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reccos.admin.dto.PathDTO;
import com.reccos.admin.model.Player;
import com.reccos.admin.service.PlayerService;
import com.reccos.admin.utils.UploadService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class PlayerController {

	@Autowired
	private PlayerService service;

	@Autowired
	private UploadService upload;

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
	public ResponseEntity<List<Player>> searchByChar(@RequestParam(name = "key") String key) {
		return ResponseEntity.ok(service.searchByChar(key));
	}

	@GetMapping("/players/find/team_name")
	public ResponseEntity<List<Player>> findByName(@RequestParam(name = "name") String name) {
		System.out.println("key = " + name);
		return ResponseEntity.ok(service.findByName(name));
	}

	@GetMapping("/players")
	public ResponseEntity<Page<Player>> listarTodos(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "12") int size) {
		Page<Player> list = service.listAllPaginate(page, size);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping("/players")
	public ResponseEntity<Player> criarTime(@RequestBody Player atleta) {
		Player obj = service.create(atleta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PostMapping("/players/upload/{slug}")
	public ResponseEntity<PathDTO> uploadFoto(@RequestParam(name = "file") MultipartFile file,
			@PathVariable String slug){
		System.out.println("debug string slug: "+slug);
		String path = upload.uploadImage(file, slug);
		if (path != null) {
			PathDTO pathDto = new PathDTO();
			pathDto.setPathToFile(path);
			return ResponseEntity.ok().body(pathDto);
		}
		return ResponseEntity.badRequest().build();
	}

	@PutMapping("/players/{id}")
	public ResponseEntity<Player> atualizarTime(@PathVariable Long id, @RequestBody Player atleta) {
		Player obj = service.update(id, atleta);

		if (obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/players/{id}")
	public ResponseEntity<Player> deletarTime(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
