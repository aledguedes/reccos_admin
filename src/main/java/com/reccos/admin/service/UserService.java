package com.reccos.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.model.User;
import com.reccos.admin.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

//	@Autowired
//	private BCryptPasswordEncoder encoder;
	
	public User recuperarUser(User original) {
		User user = repository.findByLoginOrPassword(original.getLogin(), original.getEmail());
		if (user != null)
			if (user.getPassword().equals(original.getPassword())) {
				user.setPassword(null);
				return user;
			}
		return null;
	}

	public ArrayList<User> getAll() {
		return (ArrayList<User>) repository.findAll();
	}

	public User newUser(User novo) {
		if (novo.getLogin().length() > 0 && novo.getPassword().length() > 0) {
//			novo.setAtivo(1);
			try {
				novo.setId(null);
//				novo.setPassword(encoder.encode(novo.getPassword()));
				repository.save(novo);
				return novo;
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
		return null;
	}

	public User atualizarUser(User user) {
		// TODO Auto-generated method stub
		try {
			repository.save(user);
			return user;
		}
		catch(Exception ex) {
			return null;
		}		
	}


	public User getById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}
}
