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

	@Column(name = "cep")
	private String cep;

	@Column(name = "logradouro")
	private String logradouro;

	@Column(name = "numero")
	private Integer numero;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "uf")
	private String uf;

	@Column(name = "img_scudo")
	private String img_scudo;

	@Column(name = "img_stadium")
	private String img_stadium;

	@Column(name = "phone")
	private String phone;

	@Column(name = "status")
	private Boolean status;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "teams")
	@JsonIgnore
	private Set<Federation> federation = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "teams")
//	@JsonIgnore
	private Set<League> leagues = new HashSet<>();

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("team")
	private List<Contract> contratos;

	public Team() {
		super();
	}

	public Team(long id, String name, String surname, LocalDate dt_nascimento, String initials, String president,
			String cep, String logradouro, Integer numero, String bairro, String complemento, String cidade, String uf,
			String img_scudo, String img_stadium, String phone, Boolean status, Set<Federation> federation,
			Set<League> leagues, List<Contract> contratos) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dt_nascimento = dt_nascimento;
		this.initials = initials;
		this.president = president;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cidade = cidade;
		this.uf = uf;
		this.img_scudo = img_scudo;
		this.img_stadium = img_stadium;
		this.phone = phone;
		this.status = status;
		this.federation = federation;
		this.leagues = leagues;
		this.contratos = contratos;
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

	public Set<Federation> getFederation() {
		return federation;
	}

	public void setFederation(Set<Federation> federation) {
		this.federation = federation;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getImg_scudo() {
		return img_scudo;
	}

	public void setImg_scudo(String img_scudo) {
		this.img_scudo = img_scudo;
	}

	public String getImg_stadium() {
		return img_stadium;
	}

	public void setImg_stadium(String img_stadium) {
		this.img_stadium = img_stadium;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<Contract> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contract> contratos) {
		this.contratos = contratos;
	}

	public Set<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(Set<League> leagues) {
		this.leagues = leagues;
	}
}
