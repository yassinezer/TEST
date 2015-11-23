package com.task.test.Dao;

import java.util.List;

import com.task.test.Model.Department;
import com.task.test.Model.Employee;

public interface DataBase {

	List<Department> getAllDepartments();

	List<Employee> getAllEmployees(int departmentId);

	void AddDepartment(String name);

	void AddEmployee(String firstname, String lastname, double salary, int departmentId);

	void EditEmployee(String firstname, String lastname, double salary, Long employeeId);

}
