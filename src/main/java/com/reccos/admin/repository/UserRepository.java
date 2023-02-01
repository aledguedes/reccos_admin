package com.reccos.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByLoginOrEmail(String login, String email);
}
