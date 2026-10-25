
package com.nt.dao;

import java.util.List;

import com.nt.modal.Employee;

public interface IEmployeeDAO {

	public List<Employee> showEmployeeByDesgs(String desg1,String desg2,String desg3 )throws Exception;
	
	 public int insert(Employee emp)throws Exception; 

}
