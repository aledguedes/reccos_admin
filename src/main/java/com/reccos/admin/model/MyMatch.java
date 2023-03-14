package com.reccos.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "my_matches")
public class MyMatch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "match_id")
//	@JsonIgnoreProperties({"league", "contratos"})
	private Match matches;

	@ManyToOne
	@JoinColumn(name = "player_id")
	@JsonIgnoreProperties("team")
	private Player playeres;

	public MyMatch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyMatch(Long id, Match matches, Player playeres) {
		super();
		this.id = id;
		this.matches = matches;
		this.playeres = playeres;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Match getMatches() {
		return matches;
	}

	public void setMatches(Match matches) {
		this.matches = matches;
	}

	public Player getPlayeres() {
		return playeres;
	}

	public void setPlayeres(Player playeres) {
		this.playeres = playeres;
	}

}
