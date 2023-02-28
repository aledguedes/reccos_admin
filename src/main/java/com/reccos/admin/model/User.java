package com.reccos.admin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String login;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@Column(name = "federation_id")
	private Integer federation;

	@Column(name = "cod_token")
	private String codToken;

	@Temporal(TemporalType.TIMESTAMP)
	private Date validationToken;

	public User() {
		super();
	}

	public User(Long id, @NotBlank @Size(max = 20) String login, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password, Integer federation, String codToken, Date validationToken) {
		super();
		this.id = id;
		this.login = login;
		this.email = email;
		this.password = password;
		this.federation = federation;
		this.codToken = codToken;
		this.validationToken = validationToken;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCodToken() {
		return codToken;
	}

	public void setCodToken(String codToken) {
		this.codToken = codToken;
	}

	public Date getValidationToken() {
		return validationToken;
	}

	public void setValidationToken(Date validationToken) {
		this.validationToken = validationToken;
	}

	public Integer getFederation() {
		return federation;
	}

	public void setFederation(Integer federation) {
		this.federation = federation;
	}

}
