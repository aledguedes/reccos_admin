package com.reccos.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stadium")

public class Stadium {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "img_stadium")
	private String img_stadium;

	public Stadium() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stadium(long id, String name, String surname, String img_stadium) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.img_stadium = img_stadium;
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

	public String getImg_stadium() {
		return img_stadium;
	}

	public void setImg_stadium(String img_stadium) {
		this.img_stadium = img_stadium;
	}

}
