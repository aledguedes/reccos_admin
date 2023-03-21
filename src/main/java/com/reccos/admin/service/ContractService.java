package com.reccos.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Contract;
import com.reccos.admin.model.Player;
import com.reccos.admin.model.Team;
import com.reccos.admin.repository.ContractRepository;
import com.reccos.admin.repository.PlayerRepository;

@Service
public class ContractService {

	@Autowired
	private ContractRepository repository;
	
	@Autowired
	private PlayerRepository pRepository;
	
	@Autowired
	private TeamService tService;
	
	public Contract listById(Long id) {
		Optional<Contract> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! CONTRACT ID " + id));
	}

	public List<Contract> listAll() {
		return repository.findAll();
	}

	public Contract create(Contract contract) {
		Contract c = new Contract();
		Player player = new Player();
		player = pRepository.save(contract.getPlayer());
		c.setPlayer(player);
		return repository.save(contract);
	}

	public Contract update(long id, Contract contract) {
		contract.setId(id);
		return repository.save(contract);
	}

	public Contract createTeamPlayer(Contract contract, long id) {
		Contract c = new Contract();
		Player player = new Player();
		
		Team team = tService.listById(id);
		player = pRepository.save(contract.getPlayer());
		player.setTeam_name(team.getSurname());
		c.setTeam(team);
		c.setPlayer(player);
		return repository.save(contract);
	}
}
