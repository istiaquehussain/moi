package com.coe.moi.core.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
	@GeneratedValue
	private Long id;
	private String fName;
	private String lName;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String fName, String lName) {
		super();
		this.fName = fName;
		this.lName = lName;
	}
	public Long getId() {
		return id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", fName=" + fName + ", lName=" + lName + "]";
	}
	

}
