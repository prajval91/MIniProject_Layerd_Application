package com.nt.service;

import java.util.List;

import com.nt.modal.Employee;

public interface IEmployeeMgmtService {

	public List<Employee> fetchEmployeesByDegs(String desg1,String desg2,String degs3)throws Exception;
	 public String registerEmployee(Employee emp)throws Exception; 

}
