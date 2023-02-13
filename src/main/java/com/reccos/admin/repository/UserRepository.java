package com.reccos.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailOrPassword(String email, String password);
	
	User findByEmail(String email);

	User findByEmailAndCodToken(String email, String codToken);
	
}
