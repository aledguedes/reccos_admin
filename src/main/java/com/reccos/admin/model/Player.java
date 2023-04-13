package com.reccos.admin.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "players")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String surname;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dt_nascimento;
	private Integer position;
	private Boolean status;
	private String cpf;
	private String rg;
	private String img_player;
	private String team_name;
	private boolean suspend;

	public Player() {
		super();
	}

	public Player(Long id, String name, String surname, LocalDate dt_nascimento, Integer position, Boolean status,
			String cpf, String rg, String img_player, String team_name, boolean suspend) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dt_nascimento = dt_nascimento;
		this.position = position;
		this.status = status;
		this.cpf = cpf;
		this.rg = rg;
		this.img_player = img_player;
		this.team_name = team_name;
		this.suspend = suspend;
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

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public LocalDate getDt_nascimento() {
		return dt_nascimento;
	}

	public void setDt_nascimento(LocalDate dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getImg_player() {
		return img_player;
	}

	public void setImg_player(String img_player) {
		this.img_player = img_player;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public boolean isSuspend() {
		return suspend;
	}

	public void setSuspend(boolean suspend) {
		this.suspend = suspend;
	}
}