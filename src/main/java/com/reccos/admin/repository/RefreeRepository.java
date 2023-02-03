package com.reccos.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.Refree;

public interface RefreeRepository extends JpaRepository<Refree, Long> {

	List<Refree> findByStatus(boolean status);

}
