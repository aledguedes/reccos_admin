package com.reccos.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}
