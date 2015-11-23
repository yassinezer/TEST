package com.task.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.task.test.Model.Department;
import com.task.test.Model.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")
@SuppressWarnings({"unchecked" ,"static-access"})
public class controllerTest {
	
    private MockMvc mockMvc;


    @Autowired
	private RestTemplate restoperations ;


	private MockRestServiceServer mockServer;

	@Autowired
	private controller control;


    @Before
    public void setUp() 
    {
		this.mockMvc = MockMvcBuilders.standaloneSetup(control).build();
		this.mockServer = MockRestServiceServer.createServer(restoperations);
		Assert.assertNotNull(restoperations);
    }

	@Test
	public final void testGetAllDepartments() throws Exception 
	{
		
		
		String responseBody = "[{\"departmentId\":\"1\",\"departmentName\":\"Arts and Lectures\",\"employees\":[]}]";
		Department dep = new Department();
		dep.setDepartmentId(1);
		dep.setDepartmentName("Arts and Lectures");
		
		this.mockServer.expect(requestTo(control.uri)).andExpect(method(HttpMethod.GET))
		.andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));
		List<Department> listdep =  (List<Department>) control.getAllDepartments().getModelMap().get("list");
		assertEquals(dep.getDepartmentName(), listdep.iterator().next().getDepartmentName());
		this.mockServer.verify();
	}
	
	@Test
	public final void testGetAllEmployees() throws Exception {
		
		String responseBody = "[{\"employeeId\":\"1\",\"firstname\":\"Addison\",\"lastname\":\"Timlin\",\"salary\":\"5000.5\"}]";
		Employee emp = new Employee();
		emp.setEmployeeId(1L);
		emp.setFirstname("Addison");
		emp.setLastname("Timlin");
		emp.setSalary(5000.5);
		this.mockServer.expect(requestTo(control.uri+"1/employees")).andExpect(method(HttpMethod.GET))
		.andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));
		List<Employee> listemp =  (List<Employee>) control.getAllEmployees(1).getModelMap().get("list");
		assertEquals(emp.getFirstname(), listemp.iterator().next().getFirstname());
		this.mockServer.verify();
	}

	@Test
	public final void testAddEmploye() throws Exception {
    	Assert.assertEquals(control.addEmploye(1).getViewName() , "AddEmploye");

        this.mockMvc.perform(get("/departments/{departmentId}/employees/add/" , 1))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("departmentId"))
        .andExpect(view().name("AddEmploye"));

	}

	@Test
	public final void testAddEmployee() {
		this.mockServer.expect(requestTo(control.uri+"1/employees/add")).andExpect(method(HttpMethod.POST))
		.andExpect(content().string("firstname=firstname&lastname=lastname&salary=500.0&departmentId=1"))
		.andRespond(withSuccess());
		Assert.assertEquals("redirect:/app/departments/1/employees",
				control.AddEmployee(1, "firstname", "lastname", 500.0).getViewName());
		this.mockServer.verify();
	}

	@Test
	public final void testEditEmployeeIntLong() throws Exception {
    	Assert.assertEquals(control.EditEmployee(1, 1).getViewName() , "EditEmployee");

        this.mockMvc.perform(get("/departments/{departmentId}/employees/{employeeId}/edit" , 1 , 1))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("departmentId"))
        .andExpect(model().attributeExists("employeeId"))
        .andExpect(view().name("EditEmployee"));
	}

	@Test
	public final void testEditEmployeeIntLongStringStringDouble() {
		this.mockServer.expect(requestTo(control.uri+"1/employees/1/edit")).andExpect(method(HttpMethod.PUT))
		.andExpect(content().string("firstname=firstname&lastname=lastname&salary=500.0&employeeId=1"))
		.andRespond(withSuccess());
		Assert.assertEquals("redirect:/app/departments/1/employees",
				control.EditEmployee(1,1 , "firstname", "lastname", 500.0).getViewName());
		this.mockServer.verify();

	}

}
