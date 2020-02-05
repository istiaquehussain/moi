package com.coe.moi.core.entity;

public class QueMessage {
	private String authId;
	private String ioId;
	private String type;
	private String action;
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	public String getIoId() {
		return ioId;
	}
	public void setIoId(String ioId) {
		this.ioId = ioId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "QueMessage [authId=" + authId + ", ioId=" + ioId + ", type=" + type + ", action=" + action + "]";
	}
	
	
}
