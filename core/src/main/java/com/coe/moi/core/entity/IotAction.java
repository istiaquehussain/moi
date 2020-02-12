package com.coe.moi.core.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Entity
@Table(name = "IOT_ACTION")
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class IotAction {
	@Id @GeneratedValue
	Long id;
	String ioId;
	String ioMode;
	String ioAction;
	@ManyToOne (
			 optional = false
			)
	@JoinColumn(name="bid",
			nullable = false
			)
	Board board;
	@ManyToOne(
			 optional = false
			)
	@JoinColumn(name="uid",
			nullable = false
			)
	UserProfile user;
	
	
	
	public IotAction(String ioId, String ioMode, String ioAction) {
		super();
		this.ioId = ioId;
		this.ioMode = ioMode;
		this.ioAction = ioAction;
	}
	
	
	public IotAction(Long id, String ioId, String ioMode, String ioAction, Board board, UserProfile user) {
		super();
		this.id = id;
		this.ioId = ioId;
		this.ioMode = ioMode;
		this.ioAction = ioAction;
		this.board = board;
		this.user = user;
	}


	public IotAction() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIoId() {
		return ioId;
	}
	public void setIoId(String ioId) {
		this.ioId = ioId;
	}
	public String getIoMode() {
		return ioMode;
	}
	public void setIoMode(String ioMode) {
		this.ioMode = ioMode;
	}
	public String getIoAction() {
		return ioAction;
	}
	public void setIoAction(String ioAction) {
		this.ioAction = ioAction;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public UserProfile getUser() {
		return user;
	}
	public void setUser(UserProfile user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "IotAction [id=" + id + ", ioId=" + ioId + ", ioMode=" + ioMode + ", ioAction=" + ioAction + ", board="
				+ board + ", user=" + user + "]";
	}
	
	
	
}
