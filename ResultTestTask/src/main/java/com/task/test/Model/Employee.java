package com.task.test.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.ws.rs.DefaultValue;

import com.fasterxml.jackson.annotation.JsonBackReference;
 
 
@Entity
@Table
public class Employee implements java.io.Serializable{
 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id" ,  length = 10 )
    private Long employeeId;
     
    @Column(name="firstname" , length = 50 , nullable= false)
    @DefaultValue(value = "null")
    private String firstname;
     
    @Column(name="lastname" , length = 50 , nullable= false)
    @DefaultValue(value = "null")
    private String lastname;
     
    @Column(name="salary", nullable= false )
    private double salary;
    @ManyToOne
    @JoinColumn(name="department_id" , nullable = false )
    @JsonBackReference
    private Department department;
     
    public Employee() {
         
    }

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
 
}