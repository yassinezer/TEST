package com.task.test.Model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonManagedReference;
 
@Entity
@Table
public class Department implements Serializable {
 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="department_id" , length = 10 )
    private int departmentId;
     
    @Column(name="DEPT_NAME" , nullable = false , length = 50 )
    @ColumnDefault(value = "0")
    private String departmentName;
    @OneToMany(fetch = FetchType.EAGER , mappedBy ="department" )
    @Cascade({CascadeType.DELETE})
    @JsonManagedReference
    private Set<Employee> employees ;
    
    public Department (){}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Set<Employee> getEmployees() {
		
		return employees;
	}

	
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
		
	}
 
	
    
}