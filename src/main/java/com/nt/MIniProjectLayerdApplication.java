package com.nt;


import java.util.List;
import java.util.Scanner;
import com.nt.modal.Employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.nt.controller.EmployeeOperationsController;

@SpringBootApplication
public class MIniProjectLayerdApplication {

	public static void main(String[] args) {
		
		EmployeeOperationsController controller=null;
		//read the desg from the end user
		
		Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Desg1::");
        String desg1=sc.next();
        
        System.out.println("Enter the Desg2::");
        String desg2=sc.next();
        
        System.out.println("Enter the Desg3::");
        String desg3=sc.next();

      
		//get IOC Container 
        try(ConfigurableApplicationContext ctx=SpringApplication.run(MIniProjectLayerdApplication.class, args)){
        	
    		//get contoller class obj ref
    			 controller=ctx.getBean("empController",EmployeeOperationsController.class);
    				
    				try{
    					List<Employee> list=controller.processEmployeeBYDes(desg1, desg2, desg3);
    					list.forEach(emp->{
    						System.out.println(emp);
    					});
    				
    		
    		System.out.println("=======================Resigetraton process=================");
    		 System.out.println("Enter the name::");
    	        String name=sc.next();
    	        
    	        System.out.println("Enter the desg::");
    	        String desg=sc.next();
    	        
    	        System.out.println("Enter the salary::");
    	        float salary=sc.nextFloat();
    		
    	        
    	        try{
    	        	Employee emp=new Employee();
    	        	emp.setEname(name);
    	        	emp.setDesg(desg);
    	        	emp.setSalary(salary);
    	        	
					// i added for this temp
					  String msg=controller.addEmployee(emp); System.out.println(msg);
					 
    	        	}//end of the try3
    	        catch(Exception e)
    	        {
    	        	e.printStackTrace();
    	        }
    	        
    				} //end of the try2
    				catch(Exception e){
    					e.printStackTrace();
    				}
    		}//end of the try1
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    				
    	        
    				//close IOC Container
//   				ctx.close();  //not need to close becase already close with try with resource
    	}

}
