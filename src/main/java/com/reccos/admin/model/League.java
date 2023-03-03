package com.reccos.admin.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "leagues")
public class League {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "dt_start")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dt_start;

	@Column(name = "dt_end")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dt_end;

	@Column(name = "league_system")
	private String league_system;

	@Column(name = "league_mode")
	private String league_mode;

	@Column(name = "max_teams")
	private Integer max_teams;

	@Column(name = "max_players")
	private Integer max_players;

	@Column(name = "min_teams")
	private Integer min_teams;

	@Column(name = "min_players")
	private Integer min_players;

	@Column(name = "qt_group")
	private Integer qt_group;

	@Column(name = "status")
	private Boolean status;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "leagues_teams", joinColumns = { @JoinColumn(name = "league_id") }, inverseJoinColumns = {
			@JoinColumn(name = "team_id") })
	private Set<Team> teams = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "leagues_rounds", joinColumns = { @JoinColumn(name = "league_id") }, inverseJoinColumns = {
			@JoinColumn(name = "round_id") })
	private Set<Round> rounds = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "federation_id")
	@JsonIgnore
	private Federation federation;

	public League() {
		super();
	}

	public League(long id, String name, LocalDate dt_start, LocalDate dt_end, String league_system, String league_mode,
			Integer max_teams, Integer max_players, Integer min_teams, Integer min_players, Integer qt_group,
			Boolean status, Set<Team> teams, Set<Round> rounds, Federation federation) {
		super();
		this.id = id;
		this.name = name;
		this.dt_start = dt_start;
		this.dt_end = dt_end;
		this.league_system = league_system;
		this.league_mode = league_mode;
		this.max_teams = max_teams;
		this.max_players = max_players;
		this.min_teams = min_teams;
		this.min_players = min_players;
		this.qt_group = qt_group;
		this.status = status;
		this.teams = teams;
		this.rounds = rounds;
		this.federation = federation;
	}

	public Integer getQt_group() {
		return qt_group;
	}

	public void setQt_group(Integer qt_group) {
		this.qt_group = qt_group;
	}

	public Integer getMax_teams() {
		return max_teams;
	}

	public void setMax_teams(Integer max_teams) {
		this.max_teams = max_teams;
	}

	public Integer getMax_players() {
		return max_players;
	}

	public void setMax_players(Integer max_players) {
		this.max_players = max_players;
	}

	public Integer getMin_teams() {
		return min_teams;
	}

	public void setMin_teams(Integer min_teams) {
		this.min_teams = min_teams;
	}

	public Integer getMin_players() {
		return min_players;
	}

	public void setMin_players(Integer min_players) {
		this.min_players = min_players;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	public LocalDate getDt_start() {
		return dt_start;
	}

	public void setDt_start(LocalDate dt_start) {
		this.dt_start = dt_start;
	}

	public LocalDate getDt_end() {
		return dt_end;
	}

	public void setDt_end(LocalDate dt_end) {
		this.dt_end = dt_end;
	}

	public String getLeague_system() {
		return league_system;
	}

	public void setLeague_system(String league_system) {
		this.league_system = league_system;
	}

	public String getLeague_mode() {
		return league_mode;
	}

	public Federation getFederation() {
		return federation;
	}

	public void setFederation(Federation federation) {
		this.federation = federation;
	}

	public void setLeague_mode(String league_mode) {
		this.league_mode = league_mode;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<Round> getRounds() {
		return rounds;
	}

	public void setRounds(Set<Round> rounds) {
		this.rounds = rounds;
	}

	public void addTag(Team tag) {
		this.teams.add(tag);
		tag.getLeagues().add(this);
	}

	public void removeTag(long tagId) {
		Team tag = this.teams.stream().filter(t -> t.getId() == tagId).findFirst().orElse(null);
		if (tag != null) {
			this.teams.remove(tag);
			tag.getLeagues().remove(this);
		}
	}
}
