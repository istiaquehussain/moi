package com.coe.moi.core.entity;

public class Action {
	Long id;
	String ioId;
	String ioMode;
	String ioAction;
	Long boardId;
	Long userId;
	public Action(Long id, String ioId, String ioMode, String ioAction, Long boardId, Long userId) {
		super();
		this.id = id;
		this.ioId = ioId;
		this.ioMode = ioMode;
		this.ioAction = ioAction;
		this.boardId = boardId;
		this.userId = userId;
	}
	public Action() {
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
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "IotAction [id=" + id + ", ioId=" + ioId + ", ioMode=" + ioMode + ", ioAction=" + ioAction + ", boardId="
				+ boardId + ", userId=" + userId + "]";
	} 
	
	
	
	
	
	
}
