package com.employee.webservice.rest;

public class Employee {
	
	int eID;
	String fName;
	String lName;
	int ssn;
	String dob;
	
	
	public Employee(int eID, String fName, String lName, int ssn, String dob) {
		super();
		this.eID = eID;
		this.fName = fName;
		this.lName = lName;
		this.ssn = ssn;
		this.dob = dob;
	}
	
	public int geteID() {
		return eID;
	}
	public void seteID(int eID) {
		this.eID = eID;
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
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}

	
	
}
