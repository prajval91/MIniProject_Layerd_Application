package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.modal.Employee;
import com.nt.dao.IEmployeeDAO;
import com.nt.modal.Employee;

@Service("empService")

public class EmployeeMgmtSercieImpl implements IEmployeeMgmtService {
	
	@Autowired
	private IEmployeeDAO empDAO;

	@Override
	public List<Employee> fetchEmployeesByDegs(String desg1, String desg2, String degs3) throws Exception {
		// convert give degs to uppercase letters
		desg1 = desg1.trim().toUpperCase();
		desg2 = desg2.trim().toUpperCase();
		degs3 = degs3.trim().toUpperCase();
		
		List<Employee> list=empDAO.showEmployeeByDesgs(desg1, desg2, degs3);
		
		//calculate gross salary and netsalary of the employee
		
		list.forEach(
				emp->{
					emp.setGrossSalary(emp.getSalary()+(emp.getSalary()*0.5f));
					emp.setNetSalary(emp.getGrossSalary()-(emp.getGrossSalary()*0.2f));
				});
		
		return list;
	}

	
	  @Override 
	  public String registerEmployee(Employee emp) throws Exception {
			//use DAO
			int result=empDAO.insert(emp);
			return result==0?"Employee not inserted":"Employee is inserted";
	  }
	 
}
