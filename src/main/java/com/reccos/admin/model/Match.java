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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "matches")
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer idd_match;

	@Column(name = "match_date")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate match_date;

	@ManyToOne
	@JsonIgnoreProperties("contratos")
	private Team home_team;

	@ManyToOne
	@JsonIgnoreProperties("contratos")
	private Team visiting_team;

//	@ManyToOne
//	private Team head_referee;
//	@ManyToOne
//	private Team assistant_referee;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL, CascadeType.MERGE }, mappedBy = "matches")
	@JsonIgnore
	private Set<Round> rounds = new HashSet<>();

	public Match() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Match(Long id, Integer idd_match, LocalDate match_date, Team home_team, Team visiting_team,
			Set<Round> rounds) {
		super();
		this.id = id;
		this.idd_match = idd_match;
		this.match_date = match_date;
		this.home_team = home_team;
		this.visiting_team = visiting_team;
		this.rounds = rounds;
	}

	public Integer getIdd_match() {
		return idd_match;
	}

	public void setIdd_match(Integer idd_match) {
		this.idd_match = idd_match;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getMatch_date() {
		return match_date;
	}

	public void setMatch_date(LocalDate match_date) {
		this.match_date = match_date;
	}

	public Team getHome_team() {
		return home_team;
	}

	public void setHome_team(Team home_team) {
		this.home_team = home_team;
	}

	public Team getVisiting_team() {
		return visiting_team;
	}

	public void setVisiting_team(Team visiting_team) {
		this.visiting_team = visiting_team;
	}

	public Set<Round> getRounds() {
		return rounds;
	}

	public void setRounds(Set<Round> rounds) {
		this.rounds = rounds;
	}

}
