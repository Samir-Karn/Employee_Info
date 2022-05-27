package com.employee.webservice.rest;

import java.sql.Connection;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DatabaseConnection;

@RestController
public class crudEmployee {

	@RequestMapping(value = "/addemp", method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addEmployee(@RequestBody Employee x) {
		int eID = x.eID;
		String fName = x.fName;
		String lName = x.lName;
		int ssn = x.ssn;
		String dob = x.dob;
		
		
		boolean empCheck = false;
		try {
			Connection conn=DatabaseConnection.getDBConnection();
			empCheck=employeeTable.checkEmp(eID, conn);
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if (empCheck == false)
		{
			try {
				Connection conn=DatabaseConnection.getDBConnection();
				employeeTable.registerEmployee(eID, fName, lName, ssn, dob, conn);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return "Employee Added to database with Employee ID: " + eID;
		}
		else {
			return "Employee Already Exists";
		}
	}
	
	@RequestMapping(value = "/updateemp", method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateEmp(@RequestBody Employee x) {
		int eID = x.eID;
		String fName = x.fName;
		String lName = x.lName;
		int ssn = x.ssn;
		String dob = x.dob;
		
		
		boolean empCheck = false;
		try {
			Connection conn=DatabaseConnection.getDBConnection();
			empCheck=employeeTable.checkEmp(eID, conn);
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if (empCheck == true)
		{
			try {
				Connection conn=DatabaseConnection.getDBConnection();
				employeeTable.updateEmployee(eID, fName, lName, ssn, dob, conn);


			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return "The Employee details has been updated with id: "+ eID;
		}
		else {
			return "The Employee doesn't exist";
		}
	}
	
	
	@RequestMapping(value = "/delemp/{eID}", method=RequestMethod.DELETE)
	public String deleteemp(@PathVariable(name="eID") int eID) throws Exception {
		int eiD = eID;
		
		boolean empCheck = false;
		try {
			Connection conn=DatabaseConnection.getDBConnection();
			empCheck=employeeTable.checkEmp(eiD, conn);
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if (empCheck == true)
		{
			try {
				Connection conn=DatabaseConnection.getDBConnection();
				employeeTable.deleteEmployee(eiD, conn);


			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return "The Employee Was Deleted with Employee ID: " + eiD;
		}
		else {
			return "The Employee doesn't exist";
		}
	}
	
	
	@RequestMapping(value = "/search/{eID}", method=RequestMethod.GET
			, produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee searchEmp(@PathVariable(name="eID") int eID) throws Exception {
		int eiD = eID;

		boolean empCheck = false;
		try {
			Connection conn=DatabaseConnection.getDBConnection();
			empCheck=employeeTable.checkEmp(eiD, conn);
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if (empCheck == true)
			{
			try {
				Connection conn=DatabaseConnection.getDBConnection();
				List<Employee> emp = employeeTable.showEmployee(eiD, conn);
				System.out.println(emp.get(0).eID);
				Employee search = new Employee(emp.get(0).eID, emp.get(0).fName, emp.get(0).lName, emp.get(0).ssn, emp.get(0).dob);
				return search;
				}
			catch (Exception e) 
				{
				e.printStackTrace();
				return null;
				}
			}
		
			else 
			{
				return null;
			}
		
	}
}
