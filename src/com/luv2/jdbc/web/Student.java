package com.luv2.jdbc.web;

public class Student {
	
   private int id;
   private String first_name;
   private String last_name;

private String email;
   @Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + first_name + ", lastName=" + last_name + ", email=" + email + "]";
	}	
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFirst_name() {
	return first_name;
}
public void setFirst_name(String first_name) {
	this.first_name = first_name;
}
public String getLast_name() {
	return last_name;
}
public void setLast_name(String last_name) {
	this.last_name = last_name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
 


public Student(String first_name, String last_name, String email) {
	super();
	this.first_name = first_name;
	this.last_name = last_name;
	this.email = email;
}
public Student(int id, String first_name, String last_name, String email) {
	super();
	this.id = id;
	this.first_name = first_name;
	this.last_name = last_name;
	this.email = email;
}
 
 
}
