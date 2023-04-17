package com.reccos.admin.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "stadium")
public class Stadium {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "img_stadium")
	private String img_stadium;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "teams_id")
//	@JsonIgnore
	@OneToMany
//	@JoinColumn(name = "stadium_id")
	@JsonIgnoreProperties("contracts")
	private List<Team> teams;

	public Stadium() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stadium(Long id, String name, String surname, String img_stadium, List<Team> teams) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.img_stadium = img_stadium;
		this.teams = teams;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getImg_stadium() {
		return img_stadium;
	}

	public void setImg_stadium(String img_stadium) {
		this.img_stadium = img_stadium;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

}
