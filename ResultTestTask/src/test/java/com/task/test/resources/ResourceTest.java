package com.task.test.resources;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import com.task.test.Model.Department;
import com.task.test.Model.Employee;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResourceTest extends JerseyTest {

    @Override
	protected Application configure() {
	  ResourceConfig rf = new ResourceConfig(Resource.class);
	  rf.register(SpringLifecycleListener.class);
	  rf.property("contextConfigLocation", "file:src/main/webapp/WEB-INF/spring/root-context.xml");
	  return rf;
	}

	@Test
	public final void testGetAllDepartments() throws JsonParseException, JsonMappingException, IOException {
		final Response response = target().path("/departments")
				.request(MediaType.APPLICATION_JSON).get();
        String departments = response.readEntity(String.class);
        assertNotNull(departments);
        ObjectMapper mapper = new ObjectMapper();
        Department[] DepArray = mapper.readValue(departments, Department[].class);
        assertNotNull(DepArray);
        assertTrue(DepArray.length >= 1);
        Assert.assertEquals(200, response.getStatus());
        }

	@Test
	public final void testAddDepartment() {
		Form form = new Form() ;
        form.param("name", "Music");
        final Response rs = target("departments/add").request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
        		.post(Entity.entity(form, "application/x-www-form-urlencoded"));
        assertNotNull(rs.readEntity(String.class));
        Assert.assertEquals(200, rs.getStatus());
        }

	@Test
	public final void testGetAllEmployees() throws JsonParseException, JsonMappingException, IOException {
		final Response response = target("/departments/1/employees").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get();
        String employees = response.readEntity(String.class);
        assertNotNull(employees);
        ObjectMapper mapper = new ObjectMapper();
        Employee[] emArray = mapper.readValue(employees, Employee[].class);
        assertNotNull(emArray);
        assertTrue(emArray.length >= 1);
        Assert.assertEquals(200, response.getStatus());
	}

	@Test
	public final void testAddEmployee() {
		
        Form form = new Form() ;
        form.param("firstname", "john");
        form.param("lastname", "smith");
        form.param("salary", "550.55");
        final Response rs = target("/departments/1/employees/add")
        .request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(Entity.entity(form, "application/x-www-form-urlencoded"));
        assertNotNull(rs.readEntity(String.class));
        Assert.assertEquals(200, rs.getStatus());
	}

	@Test
	public final void testEditEmployee() {
		Form form = new Form() ;
        form.param("firstname", "will");
        form.param("lastname", "smith");
        form.param("salary", "7000");
        final Response rs = target("/departments/1/employees/1/edit")
        .request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(Entity.entity(form, "application/x-www-form-urlencoded"));
        assertNotNull(rs.readEntity(String.class));
        Assert.assertEquals(200, rs.getStatus());
		
	}

}
