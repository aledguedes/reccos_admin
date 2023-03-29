package com.reccos.admin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "goals")
public class Goal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "num_goals")
	private Integer num_goals;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "player_id", nullable = false)
//	@JsonIgnore
	private Player player;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "match_id", nullable = false)
	@JsonIgnoreProperties({"home", "visiting"})
	private Match match;

	public Goal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Goal(long id, Integer num_goals, Player player, Match match) {
		super();
		this.id = id;
		this.num_goals = num_goals;
		this.player = player;
		this.match = match;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Integer getNum_goals() {
		return num_goals;
	}

	public void setNum_goals(Integer num_goals) {
		this.num_goals = num_goals;
	}

}
