package com.reccos.admin.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "teams")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "dt_nascimento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dt_nascimento;

	@Column(name = "initials")
	private String initials;

	@Column(name = "president")
	private String president;

	@Column(name = "img_scudo")
	private String img_scudo;

	@Column(name = "status")
	private Boolean status;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "teams")
	@JsonIgnore
	private Set<Federation> federation = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "teams")
	@JsonIgnore
	private Set<League> leagues = new HashSet<>();

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("team")
	private List<Contract> contratos;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "teams_stadium", joinColumns = { @JoinColumn(name = "teams_id") }, inverseJoinColumns = {
			@JoinColumn(name = "stadium_id") })
	private Set<Stadium> stadium = new HashSet<>();

	public Team() {
		super();
	}

	public Team(long id, String name, String surname, LocalDate dt_nascimento, String initials, String president,
			String img_scudo, Boolean status, Set<Federation> federation, Set<League> leagues, List<Contract> contratos,
			Set<Stadium> stadium) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dt_nascimento = dt_nascimento;
		this.initials = initials;
		this.president = president;
		this.img_scudo = img_scudo;
		this.status = status;
		this.federation = federation;
		this.leagues = leagues;
		this.contratos = contratos;
		this.stadium = stadium;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public LocalDate getDt_nascimento() {
		return dt_nascimento;
	}

	public void setDt_nascimento(LocalDate dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getPresident() {
		return president;
	}

	public void setPresident(String president) {
		this.president = president;
	}

	public String getImg_scudo() {
		return img_scudo;
	}

	public void setImg_scudo(String img_scudo) {
		this.img_scudo = img_scudo;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<Federation> getFederation() {
		return federation;
	}

	public void setFederation(Set<Federation> federation) {
		this.federation = federation;
	}

	public Set<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(Set<League> leagues) {
		this.leagues = leagues;
	}

	public List<Contract> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contract> contratos) {
		this.contratos = contratos;
	}

	public Set<Stadium> getStadium() {
		return stadium;
	}

	public void setStadium(Set<Stadium> stadium) {
		this.stadium = stadium;
	}
}
