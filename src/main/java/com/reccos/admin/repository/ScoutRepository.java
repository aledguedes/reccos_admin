package com.reccos.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.Scout;

public interface ScoutRepository extends JpaRepository<Scout, Long> {

}
