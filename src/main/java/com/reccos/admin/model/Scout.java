package com.reccos.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "scouts")
public class Scout {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "home_goals")
	private Integer home_goals;
	
	@Column(name = "visiting_goals")
	private Integer visiting_goals;

	public Scout() {
		super();
	}

	public Scout(long id, Integer home_goals, Integer visiting_goals) {
		super();
		this.id = id;
		this.home_goals = home_goals;
		this.visiting_goals = visiting_goals;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getHome_goals() {
		return home_goals;
	}

	public void setHome_goals(Integer home_goals) {
		this.home_goals = home_goals;
	}

	public Integer getVisiting_goals() {
		return visiting_goals;
	}

	public void setVisiting_goals(Integer visiting_goals) {
		this.visiting_goals = visiting_goals;
	}

}
