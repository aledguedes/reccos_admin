package com.reccos.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Federation;
import com.reccos.admin.model.Team;
import com.reccos.admin.repository.FederationRepository;

@Service
public class FederationService {

	@Autowired
	private FederationRepository repository;

	@Autowired
	private TeamService teamService;

	public Federation listById(Long id) {
		Optional<Federation> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! FEDERATION ID " + id));
	}

	public List<Federation> listAll() {
		return repository.findAll();
	}

	public List<Federation> listByStatus(boolean status) {
		List<Federation> obj = repository.findByStatus(status);
		return obj;
	}

	public Federation create(Federation obj) {

		Federation f = new Federation();
		f.setName(obj.getName());
		f.setSurname(obj.getSurname());
		f.setStatus(obj.getStatus());
		f.getTeams().addAll(obj.getTeams().stream().map(v -> {
			Team vv = teamService.listById(v.getId());
			vv.getFederation().add(f);
			return vv;
		}).collect(Collectors.toList()));

		return repository.save(f);
	}

	public Federation update(Long id, Federation obj) {
		obj.setId(id);
		return repository.save(obj);
	}
}
