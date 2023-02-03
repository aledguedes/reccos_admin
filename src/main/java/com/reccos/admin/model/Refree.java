package com.reccos.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "refrees")
public class Refree {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String surname;
	private Boolean status;
	private String cpf;
	private String rg;
	private String img_refree;

	public Refree() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Refree(Long id, String name, String surname, Boolean status, String cpf, String rg, String img_refree) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.status = status;
		this.cpf = cpf;
		this.rg = rg;
		this.img_refree = img_refree;
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

	public String getImg_refree() {
		return img_refree;
	}

	public void setImg_refree(String img_refree) {
		this.img_refree = img_refree;
	}

}
