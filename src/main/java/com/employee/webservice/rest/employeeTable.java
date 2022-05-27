package com.employee.webservice.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.example.demo.DatabaseConnection;

public class employeeTable {

	public static void main(String[] args) {
		
		Connection conn=null;
		try {
			conn = DatabaseConnection.getDBConnection();
			conn.setAutoCommit(false);

			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}
		}finally {
			try {
				conn.close();
			}catch(Exception ee) {}
		}
	}
	
	public static void registerEmployee (int eID, String fName, String lName, int ssn, String dob, Connection conn) throws SQLException{
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement
					("insert into employeeData (eID, fName, lName, ssn, dob) values (?, ?, ?, ?, ?)");
			stmt.setInt(1, eID);
			stmt.setString(2, fName);
			stmt.setString(3, lName);
			stmt.setInt(4, ssn);
			stmt.setString(5, dob);
			int count= stmt.executeUpdate();
			System.out.println("records created:" + count);		
		}
		catch (SQLException e) {
			throw e;
		}
		finally {
			try {stmt.close();}
			catch (SQLException s) {}
		}
	}
	
	public static boolean checkEmp(int eID, Connection conn) throws SQLException{
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		boolean result = false;
		try
		{
			stmt = conn.prepareStatement
					("select * from employeeData where eID = ?");
			stmt.setLong(1,  eID);
			resultSet = stmt.executeQuery();
			if(resultSet.next())
			{
				result = true;
			}
			return result;
		}
		catch (SQLException e)
        {
        	System.out.println("Exception occired");
        	e.printStackTrace();
            throw e;
        }
        finally
        {
        	try{
        		resultSet.close(); } catch(SQLException s){}
            try{
            	stmt.close();} catch(SQLException s){}
            
        }
	}
	
	public static void updateEmployee(int eID, String fName, String lName, int ssn, String dob, Connection conn) throws SQLException{

		PreparedStatement stmt = null;
		
		try {
		stmt = conn.prepareStatement
					("update employeeData set fName = ? , lName = ?, ssn = ?, dob = ? WHERE eID = ?");
		stmt.setInt(5, eID);
		stmt.setString(1, fName);
		stmt.setString(2, lName);
		stmt.setInt(3, ssn);
		stmt.setString(4, dob);
		int count= stmt.executeUpdate();
		System.out.println("records updated:" + count);	
		}
		catch(SQLException e){
			throw e;
		}
        finally
        {
            try{
            	stmt.close();} catch(SQLException s){}
        }
	}
	
	public static void deleteEmployee(int eID,Connection conn) throws  SQLException
    {
        PreparedStatement stmt = null;
        try
        {
            stmt = conn.prepareStatement("delete from employeeData where eID = ?");
            stmt.setInt(1, eID);
            int rowsupdated=stmt.executeUpdate();
            System.out.println("records deleted:"+rowsupdated);
        }
        catch (SQLException e)
        {
        	throw e;
        }
        finally
        {
            try{
            	stmt.close();} catch(SQLException s){}
        }
     
    }
	
	public static List<Employee> showEmployee(int eID, Connection conn) throws SQLException{
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		stmt = conn.prepareStatement
					("select * from employeeData where eid = ?");
		stmt.setInt(1, eID);
			resultSet = stmt.executeQuery();
		List<Employee> emp = new LinkedList<Employee>();
		
		while (resultSet.next()) {
			int empid = resultSet.getInt("eID");
			String fName = resultSet.getString("fName");
			String lName = resultSet.getString("lName");
			int ssn = resultSet.getInt("ssn");
			String dob = resultSet.getString("dob");
			
			Employee search = new Employee(empid, fName, lName, ssn, dob);
			emp.add(search);
		}
		return emp;
	}
	
}

