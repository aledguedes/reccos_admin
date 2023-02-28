package com.reccos.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reccos.admin.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {
 
}
