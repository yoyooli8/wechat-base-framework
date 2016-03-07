package com.xa3ti.business.entity;

import javax.persistence.*;

/**
 * SigmaUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "admin")
public class Admin implements java.io.Serializable {

	// Fields

	private Long id;
	private String userName;
	private String password;

	// Constructors
	public Admin() {
		super();
	}


	
	public Admin(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}



	public Admin(Long id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}



	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "user_name", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

	
	


}