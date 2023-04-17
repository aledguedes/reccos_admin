package com.reccos.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Match;
import com.reccos.admin.model.Statistics;
import com.reccos.admin.model.Team;
import com.reccos.admin.repository.MatchRepository;

@Service
public class MatchService {

	@Autowired
	private MatchRepository repository;

	@Autowired
	private TeamService teamService;

	@Autowired
	private StatisticsService statisticService;

	public Match listById(Long id) {
		Optional<Match> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! MATCH ID " + id));
	}

	public List<Match> listAll() {
		return repository.findAll();
	}

	public Page<Match> allMatchesPage(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		return repository.findAll(paging);
	}

	public Match createNow(Match obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Match update(Long id, Match obj) {
		obj.setId(id);
		Team v = teamService.listById(obj.getVisiting().getId());
		Team h = teamService.listById(obj.getHome().getId());
		obj.setHome(h);
		obj.setStatus(false);
		obj.setVisiting(v);
		if (obj.getHome_goals() == null) {
			obj.setHome_goals(0);
		}
		if (obj.getVisiting_goals() == null) {
			obj.setVisiting_goals(0);
		}

		// salvando na entity statistics

		Statistics st_home = statisticService.statisticsByTeamId(h.getId());
		Statistics st_visi = statisticService.statisticsByTeamId(v.getId());
		if (st_home != null) {
			Integer n_match = st_home.getNum_match() + 1;
			st_home.setNum_match(n_match);
			st_visi.setNum_match(n_match);

			System.out.println("DEBUG GOLS CASA: " + obj.getHome_goals());
			System.out.println("DEBUG GOLS VISI: " + obj.getVisiting_goals());

			// VITÓRIA MANDANTE
			if (obj.getHome_goals() > obj.getVisiting_goals()) {
				st_home.setWinners(st_home.getWinners() + 1);
				st_visi.setLosers(st_visi.getLosers() + 1);

			}
			// VITÓRIA VISITANTE
			else if (obj.getHome_goals() < obj.getVisiting_goals()) {
				st_visi.setWinners(st_visi.getWinners() + 1);
				st_home.setLosers(st_home.getLosers() + 1);
				st_home.setPoints(st_home.getPoints() + 3);
			}
			// VITÓRIA MANDANTE EMPATE
			else if (obj.getHome_goals() == obj.getVisiting_goals()) {
				st_visi.setTie(st_visi.getTie() + 1);
				st_home.setTie(st_home.getTie() + 1);

				st_home.setPoints(st_visi.getPoints() + 1);
				st_visi.setPoints(st_visi.getPoints() + 1);
			}

			// GOLS PROs E CONTRA
			st_home.setPro_goals(st_home.getPro_goals() + obj.getHome_goals());
			st_home.setOwn_goals(st_home.getOwn_goals() + obj.getVisiting_goals());

			st_visi.setPro_goals(st_visi.getPro_goals() + obj.getVisiting_goals());
			st_visi.setOwn_goals(st_visi.getOwn_goals() + obj.getHome_goals());
		}
		statisticService.update(st_visi.getId(), st_visi);
		statisticService.update(st_home.getId(), st_home);

		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public List<Match> create(List<Match> match) {
		List<Match> matches = new ArrayList<>();

		for (Match m : match) {
			matches.add(m);
		}

		Iterable<Match> persistedUser = repository.saveAll(matches);
		return repository.saveAll(persistedUser);
	}

	public List<Match> matchIdTeam(long id, int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		return repository.findMatchByHomeId(id, paging);
	}
}
