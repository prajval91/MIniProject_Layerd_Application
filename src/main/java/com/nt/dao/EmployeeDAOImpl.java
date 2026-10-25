package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nt.modal.Employee;

@Repository
public class EmployeeDAOImpl implements IEmployeeDAO {

	
	private static final String GET_EMPS_BY_DESGS= 
			  "SELECT EMPNO,ENAME,JOB,SAL FROM SYSTEM.EMP WHERE UPPER(JOB)=? OR UPPER(JOB)=? OR UPPER(JOB)=? ORDER BY JOB";


	//private static final String GET_EMPS_BY_DESGS= "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE UPPER(JOB) IN (?, ?, ?) ORDER BY JOB";
	//private static final String GET_EMPS_BY_DESGS="SELECT EMPNO,ENAME,JOB,SAL FROM EMP";
	
	 	private static final String INSERT_EMP_QUERY="INSERT INTO EMP(EMPNO,ENAME,JOB,SAL)VALUES(1,?,?,?)";
	
	
	@Autowired
	private DataSource ds;

	@Override
	public List<Employee> showEmployeeByDesgs(String desg1, String desg2, String desg3) throws Exception {
	    List<Employee> list = null;

	    try (
	        Connection con = ds.getConnection();
	        PreparedStatement ps = con.prepareStatement(GET_EMPS_BY_DESGS)
	    ) {
	        // ✅ Now printing after connection is established
	        System.out.println("Connected as: " + con.getMetaData().getUserName());

	        // Debug: check total rows
	        try (Statement stmt = con.createStatement();
	        		ResultSet rsTest = stmt.executeQuery("SELECT COUNT(*) FROM SYSTEM.EMP")) {
	            if (rsTest.next()) {
	                System.out.println("Total records in EMP: " + rsTest.getInt(1));
	            }
	        }

	        // Set query parameters
	        ps.setString(1, desg1.trim().toUpperCase());
	        ps.setString(2, desg2.trim().toUpperCase());
	        ps.setString(3, desg3.trim().toUpperCase());
	        System.out.println("Executing query with params: " + desg1 + ", " + desg2 + ", " + desg3);

	        // Execute and map result
	        try (ResultSet rs = ps.executeQuery()) {
	            list = new ArrayList<Employee>();
	            while (rs.next()) {
	                Employee emp = new Employee();
	                emp.setEno(rs.getInt("EMPNO"));
	                emp.setEname(rs.getString("ENAME"));
	                emp.setDesg(rs.getString("JOB"));
	                emp.setSalary(rs.getFloat("SAL"));
	                list.add(emp);
	                System.out.println("Fetched: " + emp);
	            }
	        }

	    } catch (SQLException se) {
	        throw se;
	    } catch (Exception e) {
	        throw e;
	    }

	    System.out.println("Final list: " + list);
	    return list;
	}



	@Override
	public int insert(Employee emp) throws Exception {
		// TODO Auto-generated method stub
		try(//get pooledJDBC Con object
				Connection con=ds.getConnection();
				
				//create JDBC Prepared statement
				PreparedStatement ps=con.prepareStatement(INSERT_EMP_QUERY);
				){
			//set values to query params
			ps.setString(1, emp.getEname());
			ps.setString(2, emp.getDesg());
			ps.setDouble(3, emp.getSalary());
			
			int result=ps.executeUpdate();
			return result;
			
		}catch(SQLException se)
		{
			se.printStackTrace();
			return 0;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
			/*return 0;*/
	}
}

	

