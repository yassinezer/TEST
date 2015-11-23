package com.task.test.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.task.test.Dao.DataBase;
import com.task.test.Model.Department;
import com.task.test.Model.Employee;
@Path("/departments" )
@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
@Produces({MediaType.APPLICATION_JSON})
public class Resource 
{
	@Inject
	public DataBase dao ;
	
	private Logger logger = Logger.getLogger(Resource.class);
	
	@GET
	public  Response getAllDepartments () 
	{
        return Response.ok().entity(new GenericEntity<List<Department>>(dao.getAllDepartments()) {}).build();
	}
	
	@POST
	@Path("/add")
	public Response addDepartment(@FormParam("name") String name)
	{
		dao.AddDepartment(name);
		logger.info("a new department with the name :  " + name +  " has been registered successfully");
		return Response.ok().build();
	}
	
	@GET
	@Path("/{departmentId}/employees")
	public Response getAllEmployees (@PathParam("departmentId") int departmentId)
	{
        return Response.ok().entity(new GenericEntity<List<Employee>>(dao.getAllEmployees(departmentId)) {}).build();
	}

	@POST
	@Path("/{departmentId}/employees/add")
	public Response addEmployee(@FormParam("firstname") String firstname, @FormParam("lastname") String lastname , 
			@FormParam("salary") double salary , @PathParam("departmentId") int departmentId)
	{
		dao.AddEmployee(firstname , lastname , salary , departmentId);
		logger.info("a new Employee has been registered successfully");
		return Response.ok().build();
	}
	
	@PUT
	@Path("/{departmentId}/employees/{employeeId}/edit")
	public Response EditEmployee(@FormParam("firstname") String firstname, @FormParam("lastname") String lastname , 
			@FormParam("salary") double salary , @PathParam("departmentId") int departmentId ,
			@PathParam("employeeId") long employeeId)
	{
		dao.EditEmployee(firstname , lastname , salary , employeeId);
		logger.info("The Employee with The Id : " + employeeId + " has been Changed ");
		return Response.ok().build();
	}
	
}
