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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	@Column(name = "status")
	private Boolean status;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "leagues_teams", joinColumns = { @JoinColumn(name = "league_id") }, inverseJoinColumns = {
			@JoinColumn(name = "team_id") })
	private Set<Team> teams = new HashSet<>();

	public League() {
		super();
		// TODO Auto-generated constructor stub
	}

	public League(long id, String name, String surname, LocalDate dt_start, LocalDate dt_end, String league_system,
			String league_mode, Boolean status, Set<Team> teams) {
		super();
		this.id = id;
		this.name = name;
		this.dt_start = dt_start;
		this.dt_end = dt_end;
		this.league_system = league_system;
		this.league_mode = league_mode;
		this.status = status;
		this.teams = teams;
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

	public void setLeague_mode(String league_mode) {
		this.league_mode = league_mode;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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
