package com.reccos.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.Federation;

public interface FederationRepository extends JpaRepository<Federation, Long> {

	List<Federation> findByStatus(boolean status);

}
