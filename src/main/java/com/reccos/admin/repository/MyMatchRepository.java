package com.reccos.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.MyMatch;

public interface MyMatchRepository extends JpaRepository<MyMatch, Long> {

}
