package com.nt.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nt.modal.Employee;
import com.nt.service.IEmployeeMgmtService;
@Controller("empController")
public class EmployeeOperationsController {
	@Autowired
	private IEmployeeMgmtService empService;
	
	public List<Employee> processEmployeeBYDes(String desg1, String desg2, String desg3)throws Exception{
		System.out.println("EmployeeOperationsController.processEmployeeBYDes()");
		//use service
		 System.out.println("Inputs:from service " + desg1 + ", " + desg2 + ", " + desg3);
		List<Employee> list=empService.fetchEmployeesByDegs(desg1, desg2, desg3);
		return list;
	}
	
	
	public String addEmployee(Employee emp)throws Exception{
		//use service
		String msg=empService.registerEmployee(emp);
		
		return msg;
		
	}
	 

}
