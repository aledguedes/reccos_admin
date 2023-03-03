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
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "rounds")
public class Round {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "dt_start")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dt_start;

	@Column(name = "league_idd")
	private Long league_idd;

	@Column(name = "dt_end")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dt_end;

	@Column(name = "status")
	private Boolean status;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "round_match", joinColumns = { @JoinColumn(name = "round_id") }, inverseJoinColumns = {
			@JoinColumn(name = "match_id") })
	private Set<Match> matches = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "rounds")
	@JsonIgnore
	private Set<League> leagues = new HashSet<>();

	public Round() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Round(Long id, LocalDate dt_start, Long league_idd, LocalDate dt_end, Boolean status, Set<Match> matches,
			Set<League> leagues) {
		super();
		this.id = id;
		this.dt_start = dt_start;
		this.league_idd = league_idd;
		this.dt_end = dt_end;
		this.status = status;
		this.matches = matches;
		this.leagues = leagues;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<Match> getMatches() {
		return matches;
	}

	public void setMatches(Set<Match> matches) {
		this.matches = matches;
	}

	public Set<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(Set<League> leagues) {
		this.leagues = leagues;
	}

	public Long getLeague_idd() {
		return league_idd;
	}

	public void setLeague_idd(Long league_idd) {
		this.league_idd = league_idd;
	}

}
