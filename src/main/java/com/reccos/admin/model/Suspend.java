package com.reccos.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "suspends")
public class Suspend {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private long idd_round;

	private long idd_player;

	private String status_suspend;

	public Suspend() {
		super();
	}

	public Suspend(Long id, long idd_round, long idd_player, String status_suspend) {
		super();
		this.id = id;
		this.idd_round = idd_round;
		this.idd_player = idd_player;
		this.status_suspend = status_suspend;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getIdd_round() {
		return idd_round;
	}

	public void setIdd_round(long idd_round) {
		this.idd_round = idd_round;
	}

	public long getIdd_player() {
		return idd_player;
	}

	public void setIdd_player(long idd_player) {
		this.idd_player = idd_player;
	}

	public String getStatus_suspend() {
		return status_suspend;
	}

	public void setStatus_suspend(String status_suspend) {
		this.status_suspend = status_suspend;
	}
}
