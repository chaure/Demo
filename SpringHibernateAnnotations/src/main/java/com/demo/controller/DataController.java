package com.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.Employee;
import com.demo.service.DataService;
import com.demo.validator.*;

@Controller

public class DataController {
	
	@Autowired
	DataService dataService;
	private String message;
	@Autowired
	EmployeeValidator validator;
	@RequestMapping("form")
	
	
	public ModelAndView getForm(@ModelAttribute Employee employee) {
		//Logger logger = Logger.getLogger(DataController.class.getName());
		//if(logger.isDebugEnabled()){
			//logger.debug("getWelcome is executed!");
		//}
		
		//logs exception
	//	logger.error("This is Error message", new Exception("Testing"));
		
		// l.debug("This is debug message");
		  //l.info("This is info message");
		  //l.warn("This is warn message");
		  //l.fatal("This is fatal message");
		 // l.error("This is error message");
		  
		  System.out.println("Your logic executed successfully....");
		return new ModelAndView("form");
	}
	
	@RequestMapping("register")
	public ModelAndView registerUser(@ModelAttribute Employee employee, BindingResult result, SessionStatus status) {
		System.out.println(employee.getFirstName());
validator.validate(employee, result);
		
		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView("form");
			return model;
			//return "addEmployee";
		}
		/*if(employee.getFirstName().isEmpty()||employee.getEmail().isEmpty()||employee.getLastName().isEmpty()||employee.getPhone().isEmpty())
		{
			System.out.println("please enter all details");
			//return new ModelAndView("form");
			ModelAndView model = new ModelAndView("form");
			model.addObject("msg", "please fill all the detail");
			
			return model;
		}
		else
		{
		dataService.insertRow(employee);
	return new ModelAndView("redirect:list");
		
	}*/
		else
		{
			dataService.insertRow(employee);
			return new ModelAndView("redirect:list");
		}
	}
	
	
	@RequestMapping("list")
	public ModelAndView getLoginInfo() {
		List<?> employeeList = dataService.getList();
		return new ModelAndView("list","employeeList",employeeList);
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView getList(@ModelAttribute Employee employee)
	{
		String fname=employee.getFirstName();
		String lname=employee.getLastName();
		System.out.println(employee.getFirstName());
		System.out.println(employee.getLastName());
		 
		if(dataService.validuser(fname,lname)==null)
		 {
			message = "HELLO "+employee.getFirstName();
			 return new ModelAndView("login", "message","Sorry, username or password error"); 
		 }
		else
		{
			return new ModelAndView("redirect:list");
			
		}
		 
		 
	}
	
	@RequestMapping("login")
	
	    public ModelAndView displayLogin(@ModelAttribute Employee employee)
	
	    {
	
	        ModelAndView model = new ModelAndView("login");
	
	        Employee loginBean = new Employee();
	
	        model.addObject("loginBean", loginBean);
	
	        return model;
	
	    }

	@RequestMapping("delete")
	public ModelAndView deleteUser(@RequestParam int id) {
		dataService.deleteRow(id);
		return new ModelAndView("redirect:list");
	}
	
	@RequestMapping("edit")
	public ModelAndView editUser(@RequestParam int id,@ModelAttribute Employee employee) {
		Employee employeeObject = dataService.getRowById(id);
		return new ModelAndView("edit","employeeObject",employeeObject);
	}
	
	@RequestMapping("update")
	public ModelAndView updateUser(@ModelAttribute Employee employee) {
		dataService.updateRow(employee);
		return new ModelAndView("redirect:list");
	}

}
