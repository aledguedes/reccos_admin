package com.reccos.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Group;
import com.reccos.admin.model.League;
import com.reccos.admin.model.Match;
import com.reccos.admin.model.Round;
import com.reccos.admin.model.Team;
import com.reccos.admin.repository.RoundRepository;

@Service
public class RoundService {

	@Autowired
	private RoundRepository repository;

	@Autowired
	private TeamService teamService;

	@Autowired
	private MatchService mService;

	@Autowired
	private GroupService gService;

	@Autowired
	private LeagueService leagueService;

	public Round listById(Long id) {
		Optional<Round> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! ROUND ID " + id));
	}

	public List<Round> listAll() {
		return repository.findAll();
	}

	public Page<Round> listAllPaginate(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		return repository.findAll(paging);
	}

	public Round create(Round obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Round update(Long id, Round obj) {
		obj.setId(id);
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Round createRound(Round obj, long id) {
		Round newRound = new Round();
		Group g = new Group();
		League l = leagueService.listById(id);
		g = gService.listById(obj.getGroup_idd());
		newRound.setDt_end(obj.getDt_end());
		newRound.setDt_start(obj.getDt_start());
//		newRound.setLeague_idd(obj.getLeague_idd());
		newRound.setGroup_idd(obj.getGroup_idd());
		newRound.setNum_round(obj.getNum_round());
		newRound.setGroup(g);
		newRound.setStatus(obj.getStatus());
		newRound.setLeague(l);

		newRound.getMatches().addAll(obj.getMatches().stream().map(v -> {
			if (v.getId() == null) {
				System.out.println("DEBUG IF: " + v.getId());
				Match mm = new Match();
				mm.setMatch_date(v.getMatch_date());
				Team team_home = teamService.listById(v.getHome().getId());
				Team team_visiting = teamService.listById(v.getVisiting().getId());
				mm.setHome(team_home);
				mm.setVisiting(team_visiting);
				mm.setIdd_match(v.getIdd_match());
				mm.getRounds().add(newRound);
				return mm;
			} else {
				Match mm = mService.listById(v.getId());
				mm.getRounds().add(newRound);
				return mm;
			}
		}).collect(Collectors.toList()));

		return repository.save(newRound);
	}

	public Page<Round> roundIdLeague(long id, int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		return repository.findRoundByLeagueId(id, paging);
	}

	public Round finishRound(Long id_round, Long id_league, Round obj) {
		Round r = listById(id_round);
		obj.setId(id_round);
		obj.setStatus(false);
		obj.setMatches(r.getMatches());

		League l = leagueService.listById(id_league);
		long r_active = id_round + 1;
		l.setRound_actv(r_active);
		leagueService.update(id_league, l);
		return repository.save(obj);
	}

}
