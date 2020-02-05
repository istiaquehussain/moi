package com.coe.moi.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "IOT_USER")
public class UserProfile {
	@Id @GeneratedValue
	Long id;
	String name;
	String password;
	
	public UserProfile(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
