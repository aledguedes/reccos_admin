package com.reccos.admin.model;

import java.time.LocalDateTime;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "federation")
public class Federation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime created_at;

	@Column(name = "update_at")
	@UpdateTimestamp
	private LocalDateTime update_at;

	@Column(name = "status")
	private Boolean status;

	@OneToMany(mappedBy = "federation")
	@JsonIgnoreProperties({ "teams", "rounds" })
	private List<League> leagues;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "federation_teams", joinColumns = { @JoinColumn(name = "federation_id") }, inverseJoinColumns = {
			@JoinColumn(name = "team_id") })
	private Set<Team> teams = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "federation_refrees", joinColumns = {
			@JoinColumn(name = "federation_id") }, inverseJoinColumns = { @JoinColumn(name = "refree_id") })
	private Set<Refree> refrees = new HashSet<>();

	public Federation() {
		super();
	}

	public Federation(long id, String name, String surname, LocalDateTime created_at, LocalDateTime update_at,
			Boolean status, List<League> leagues, Set<Team> teams, Set<Refree> refrees) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.created_at = created_at;
		this.update_at = update_at;
		this.status = status;
		this.leagues = leagues;
		this.teams = teams;
		this.refrees = refrees;
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

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(LocalDateTime update_at) {
		this.update_at = update_at;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(List<League> leagues) {
		this.leagues = leagues;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	public Set<Refree> getRefrees() {
		return refrees;
	}

	public void setRefrees(Set<Refree> refrees) {
		this.refrees = refrees;
	}

	public void addTag(Team tag) {
		this.teams.add(tag);
		tag.getFederation().add(this);
	}

	public void addRefree(Refree tag) {
		this.refrees.add(tag);
		tag.getFederation().add(this);
	}

	public void removeTag(long tagId) {
		Team tag = this.teams.stream().filter(t -> t.getId() == tagId).findFirst().orElse(null);
		if (tag != null) {
			this.teams.remove(tag);
			tag.getFederation().remove(this);
		}
	}

}
