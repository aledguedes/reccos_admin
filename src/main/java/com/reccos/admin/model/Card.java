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
@Table(name = "cards")
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "yellow_card")
	private Integer yellow_card;

	@Column(name = "red_card")
	private Integer red_card;
	
	@Column(name = "ttl_yellow", columnDefinition = "integer default 0")
	private Integer ttl_yellow;

	@Column(name = "ttl_red", columnDefinition = "integer default 0")
	private Integer ttl_red;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "player_id", nullable = false)
	private Player player;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "match_id", nullable = false)
	@JsonIgnoreProperties({ "home", "visiting" })
	private Match match;

	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Card(long id, Integer yellow_card, Integer red_card, Integer ttl_yellow, Integer ttl_red, Player player,
			Match match) {
		super();
		this.id = id;
		this.yellow_card = yellow_card;
		this.red_card = red_card;
		this.ttl_yellow = ttl_yellow;
		this.ttl_red = ttl_red;
		this.player = player;
		this.match = match;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getYellow_card() {
		return yellow_card;
	}

	public void setYellow_card(Integer yellow_card) {
		this.yellow_card = yellow_card;
	}

	public Integer getRed_card() {
		return red_card;
	}

	public void setRed_card(Integer red_card) {
		this.red_card = red_card;
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

	public Integer getTtl_yellow() {
		return ttl_yellow;
	}

	public void setTtl_yellow(Integer ttl_yellow) {
		this.ttl_yellow = ttl_yellow;
	}

	public Integer getTtl_red() {
		return ttl_red;
	}

	public void setTtl_red(Integer ttl_red) {
		this.ttl_red = ttl_red;
	}

}
