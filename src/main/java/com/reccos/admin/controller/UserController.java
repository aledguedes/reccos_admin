package com.reccos.admin.controller;

import java.util.ArrayList;

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

import com.reccos.admin.model.User;
import com.reccos.admin.security.AuthToken;
import com.reccos.admin.security.TokenUtil;
import com.reccos.admin.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/login")
	public ResponseEntity<AuthToken> loginUser(@RequestBody User user) {
		User newUser = service.recuperarUser(user);
		if (user != null) {
			AuthToken jwtToken = new AuthToken();

			jwtToken.setToken(TokenUtil.encodeToken(newUser));

			return ResponseEntity.ok(jwtToken);
		}
		return ResponseEntity.status(403).build();
		
	}
	
	@GetMapping("/users")
	public ResponseEntity<ArrayList<User>> recuperarTodos(){
		return ResponseEntity.ok(service.getAll());				
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> adicionarNovo(@RequestBody User usuario){
		User res = service.newUser(usuario);
		if (res != null) {
			return ResponseEntity.status(200).body(res);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> alterarDados(@RequestBody User usuario, @PathVariable Long id){
		usuario.setId(id);
		User res = service.atualizarUser(usuario);
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> recuperarPeloId(@PathVariable Long id){
		User res = service.getById(id);
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}
