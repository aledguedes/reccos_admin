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

@Entity
@Table(name = "cards")
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "card_color", length = 1)
	private String card_color;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "player_id", nullable = false)
//	@JsonIgnore
	private Player player;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "match_id", nullable = false)
//	@JsonIgnore
	private Match match;

	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Card(long id, String card_color, Player player, Match match) {
		super();
		this.id = id;
		this.card_color = card_color;
		this.player = player;
		this.match = match;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCard_color() {
		return card_color;
	}

	public void setCard_color(String card_color) {
		this.card_color = card_color;
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
	
	

}
