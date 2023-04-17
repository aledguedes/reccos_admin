package com.reccos.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "statistics")
public class Statistics {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "pro_goals")
	private Integer pro_goals;

	@Column(name = "own_goals")
	private Integer own_goals;

	@Column(name = "points")
	private Integer points;

	@Column(name = "num_match")
	private Integer num_match;

	@Column(name = "winners")
	private Integer winners;

	@Column(name = "losers")
	private Integer losers;

	@Column(name = "tie")
	private Integer tie;

	@ManyToOne
	@JsonIgnoreProperties({ "contratos", "contracts" })
	private Team team;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groups_id")
	@JsonIgnore
	private Group group;

	public Statistics() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Statistics(Long id, Integer pro_goals, Integer own_goals, Integer points, Integer num_match, Integer winners,
			Integer losers, Integer tie, Team team, Group group) {
		super();
		this.id = id;
		this.pro_goals = pro_goals;
		this.own_goals = own_goals;
		this.points = points;
		this.num_match = num_match;
		this.winners = winners;
		this.losers = losers;
		this.tie = tie;
		this.team = team;
		this.group = group;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPro_goals() {
		return pro_goals;
	}

	public void setPro_goals(Integer pro_goals) {
		this.pro_goals = pro_goals;
	}

	public Integer getOwn_goals() {
		return own_goals;
	}

	public void setOwn_goals(Integer own_goals) {
		this.own_goals = own_goals;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getNum_match() {
		return num_match;
	}

	public void setNum_match(Integer num_match) {
		this.num_match = num_match;
	}

	public Integer getWinners() {
		return winners;
	}

	public void setWinners(Integer winners) {
		this.winners = winners;
	}

	public Integer getLosers() {
		return losers;
	}

	public void setLosers(Integer losers) {
		this.losers = losers;
	}

	public Integer getTie() {
		return tie;
	}

	public void setTie(Integer tie) {
		this.tie = tie;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
