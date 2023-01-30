package com.reccos.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Contract;
import com.reccos.admin.repository.ContractRepository;

@Service
public class ContractService {

	@Autowired
	private ContractRepository repository;
	
	public Contract listById(Long id) {
		Optional<Contract> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! ID " + id));
	}

	public List<Contract> listAll() {
		return repository.findAll();
	}

	public Contract create(Contract contract) {
		return repository.save(contract);
	}

	public Contract update(long id, Contract contract) {
		contract.setId(id);
		return repository.save(contract);
	}
}
