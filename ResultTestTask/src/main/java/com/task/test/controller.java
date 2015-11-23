package com.task.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestOperations;
import org.springframework.web.servlet.ModelAndView;

import com.task.test.Model.Department;
import com.task.test.Model.Employee;

@RequestMapping(value="/departments")
@Controller
public class controller 
{
	
	private Logger logger = Logger.getLogger(controller.class);
	@Autowired 
	private RestOperations RestOperations;
	public final static String uri = "http://localhost:8080/test/webservices/departments/";
	@RequestMapping(method = RequestMethod.GET )
	public ModelAndView getAllDepartments()
	{

		double i = 0 , sm = 0 ;
		List <Double> listsm   = new ArrayList<Double>();
		logger.info("view List Department");
		List<Department> listdepartment = Arrays.asList(this.RestOperations.getForObject(uri, Department[].class));
		ListIterator<Department> listIteratordep= listdepartment.listIterator();
		while (listIteratordep.hasNext())
		{
			Iterator<Employee> Iteratoremp = listIteratordep.next().getEmployees().iterator() ;
			while (Iteratoremp.hasNext())
			{
				i++ ;
				sm += Iteratoremp.next().getSalary() ;
			}
			sm = sm / i ;
			listsm.add(sm);
			i = 0 ;
			sm = 0 ;
		}
		ModelAndView model = new ModelAndView("department");
		model.addObject("list", listdepartment);
		model.addObject("listsm", listsm);
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET , value="/{departmentId}/employees")
	public ModelAndView getAllEmployees(@PathVariable("departmentId") int departmentId)
	{
		logger.info("view List Employees");
		Employee[] arrayemployee =this.RestOperations.getForObject(uri+departmentId+"/employees", Employee[].class);
		List<Employee> listemployee = Arrays.asList(arrayemployee);
		ModelAndView model = new ModelAndView("employee");
		model.addObject("list", listemployee);
		return model;
	}
	
	@RequestMapping(value ="/{departmentId}/employees/add", method = RequestMethod.GET )
	public ModelAndView addEmploye(@PathVariable("departmentId") int departmentId)
	{
		ModelAndView model = new ModelAndView("AddEmploye");
		model.addObject("departmentId", departmentId);
		return model ;
	}

	
	@RequestMapping(method = RequestMethod.POST , value="/{departmentId}/employees/add")
	public ModelAndView AddEmployee(@PathVariable("departmentId") int departmentId ,
			@RequestParam ("firstname") String firstname , @RequestParam ("lastname") String lastname , 
			@RequestParam ("salary") double salary)
	{
		logger.info("adding a new Employee");
	    MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
	    parameters.add("firstname", firstname);
	    parameters.add("lastname", lastname);
	    parameters.add("salary", String.valueOf(salary).toString());
	    parameters.add("departmentId", String.valueOf(departmentId).toString());
	    RestOperations.postForObject(uri+departmentId+"/employees/add",  parameters, String.class);
		ModelAndView model = new ModelAndView("redirect:/app/departments/"+departmentId +"/employees");
		return model ;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{departmentId}/employees/{employeeId}/edit")
	public ModelAndView EditEmployee(@PathVariable("departmentId") int departmentId , @PathVariable("employeeId") long employeeId)
	{
		ModelAndView model = new ModelAndView("EditEmployee");
		model.addObject("departmentId", departmentId);
		model.addObject("employeeId", employeeId);
		return model ;
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/{departmentId}/employees/{employeeId}/edit")
	public ModelAndView EditEmployee(@PathVariable("departmentId") int departmentId ,@PathVariable("employeeId") long employeeId ,
			@RequestParam ("firstname") String firstname ,@RequestParam ("lastname") String lastname ,
			@RequestParam ("salary") double salary )
	{
		logger.info("Editing the Employee No : " + employeeId);
	    MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
	    parameters.add("firstname", firstname);
	    parameters.add("lastname", lastname);
	    parameters.add("salary", String.valueOf(salary).toString());
	    parameters.add("employeeId", String.valueOf(employeeId).toString());
	    RestOperations.put(uri+departmentId+"/employees/"+employeeId +"/edit", parameters, String.class);
		ModelAndView model = new ModelAndView("redirect:/app/departments/"+departmentId+"/employees");
		return model ;
	}
	
}
