package com.reccos.admin.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name= "contract")
public class Contract {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dt_start_contract;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dt_end_contract;
	
	private Boolean status;

	@ManyToOne
	@JoinColumn(name = "team_id")
	@JsonIgnoreProperties({"league", "contracts"})
	private Team team;

	@ManyToOne
	@JoinColumn(name = "player_id")
	@JsonIgnoreProperties("team") 
	private Player player;

	public Contract() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDt_start_contract() {
		return dt_start_contract;
	}

	public void setDt_start_contract(LocalDate dt_start_contract) {
		this.dt_start_contract = dt_start_contract;
	}

	public LocalDate getDt_end_contract() {
		return dt_end_contract;
	}

	public void setDt_end_contract(LocalDate dt_end_contract) {
		this.dt_end_contract = dt_end_contract;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Team getTeam() {
		return team;
	}
 
	public void setTeam(Team team) {
		this.team = team;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
