package com.coe.moi.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "IOT_BOARD")
public class Board {
	@Id @GeneratedValue
	Long id;
	String name;
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
	public Board(String name) {
		super();
		this.name = name;
	}
	public Board() {
		super();
	}
	
	
	
	public Board(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Board [id=" + id + ", name=" + name + "]";
	}
	
	

}
