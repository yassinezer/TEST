package com.task.test.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task.test.Model.Department;
import com.task.test.Model.Employee;


@Repository
@Transactional(rollbackFor =Exception.class)
public class DataBaseImpl implements DataBase
{
    private SessionFactory sessionFactory;
    @Autowired
    public DataBaseImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public List<Department> getAllDepartments()  {
		
		@SuppressWarnings("unchecked")
		List<Department> listdept =  sessionFactory.getCurrentSession()
                .createCriteria(Department.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
		return listdept ;
	}


	@Override
	public void AddDepartment(String name) {
		Department department = new Department();
		department.setDepartmentName(name);
		sessionFactory.getCurrentSession().save(department);
	}
	
	@Override	
	public void AddEmployee(String firstname, String lastname, double salary, int departmentId) {
		String sql = "insert into EMPLOYEE (firstname, lastname, salary, department_id) "
				+ " values (:firstname, :lastname, :salary, :department_id)";
		sessionFactory.getCurrentSession().createSQLQuery(sql).setString("firstname", firstname)
		.setString("lastname", lastname).setDouble("salary", salary).setInteger("department_id", departmentId)
		.executeUpdate();
	}

	@Override
	public List<Employee> getAllEmployees(int departmentId) {
		@SuppressWarnings("unchecked")
		List<Employee> listemp = sessionFactory.getCurrentSession().createCriteria(Employee.class).
		createAlias("department", "a").
		add(Restrictions.eq("a.departmentId", departmentId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listemp ;
	}

	@Override	
	public void EditEmployee(String firstname, String lastname, double salary, Long employeeId) {
		
		Employee employee = (Employee) sessionFactory.getCurrentSession().get(Employee.class, employeeId);
		employee.setFirstname(firstname);
		employee.setLastname(lastname);
		employee.setSalary(salary);
		sessionFactory.getCurrentSession().saveOrUpdate(employee);

	}

	
}

