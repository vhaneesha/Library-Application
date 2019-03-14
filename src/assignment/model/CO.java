package assignment.model;

import java.util.Date;

public class CO {

	Integer id;
	int cin;
	String name;
	Date borrowed;
	Date due;
	Date returned;
	
	public CO(Integer id, int cin,String name, Date borrowed, Date due, Date returned) {
		this.id = id;
		this.cin = cin;
		this.name = name;
		this.borrowed = borrowed;
		this.due = due;
		this.returned = returned;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	public Date getBorrowed() {
		return borrowed;
	}
	public void setBorrowed(Date borrowed) {
		this.borrowed = borrowed;
	}
	public Date getDue() {
		return due;
	}
	public void setDue(Date due) {
		this.due = due;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getReturned() {
		return returned;
	}
	public void setReturned(Date returned) {
		this.returned = returned;
	}
	
}
