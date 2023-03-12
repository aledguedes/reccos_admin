package com.reccos.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Federation;
import com.reccos.admin.model.Group;
import com.reccos.admin.model.League;
import com.reccos.admin.model.Team;
import com.reccos.admin.repository.LeagueRepository;

@Service
public class LeagueService {

	@Autowired
	private LeagueRepository leagueRepository;

	@Autowired
	private FederationService federationService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private GroupService groupService;

	public League listById(Long id) {
		Optional<League> obj = leagueRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! LEAGUE ID " + id));
	}

	public List<League> listAll() {
		return leagueRepository.findAll();
	}

	public List<League> listByStatus(boolean status) {
		List<League> obj = leagueRepository.findByStatus(status);
		return obj;
	}

	public List<League> findByName(String name) {
		System.out.println("BUSCA POR LETRA: " + name);
		return leagueRepository.findByNameContaining(name);
	}

	public League create(League obj) {
		return leagueRepository.save(obj);
	}

	public League update(Long id, League obj) {
		obj.setId(id);
		return leagueRepository.save(obj);
	}

	public League createLeague(League league, Long id) {
		League newLeague = new League();
		Federation f = new Federation();

		f = federationService.listById(id);

		newLeague.setId(league.getId());
		newLeague.setName(league.getName());
		newLeague.setDt_start(league.getDt_start());
		newLeague.setDt_end(league.getDt_end());
		newLeague.setLeague_system(league.getLeague_system());
		newLeague.setLeague_mode(league.getLeague_mode());
		newLeague.setStatus(league.getStatus());
		newLeague.setMax_teams(league.getMax_teams());
		newLeague.setMin_teams(league.getMin_teams());
		newLeague.setIdd_fed(id);
		newLeague.setFederation(f);
		newLeague.getTeams().addAll(league.getTeams().stream().map(v -> {
			Team mm = teamService.listById(v.getId());
			mm.getLeagues().add(newLeague);
			return mm;
		}).collect(Collectors.toList()));

		newLeague.getGroups().addAll(league.getGroups().stream().map(v -> {
			Group mm = groupService.create(v);
			mm.getLeague().add(newLeague);
			return mm;
		}).collect(Collectors.toList()));

		return leagueRepository.save(newLeague);
	}
}
