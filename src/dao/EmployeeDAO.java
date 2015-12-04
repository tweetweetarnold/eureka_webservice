package dao;

import java.util.List;

import model.Employee;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import connection.MyConnection;

public class EmployeeDAO {

	// Retrieve Employee from DB with EmployeeID
	public Employee getEmployee(int employeeId) {
		return (Employee) MyConnection.get(Employee.class, employeeId);
	}

	// Retrieve Employee from DB using Employee username
	public Employee getEmployeeByEmail(String email) {

		DetachedCriteria dc = DetachedCriteria.forClass(Employee.class);
		dc.add(Restrictions.eq("email", email));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		return (Employee) l.get(0);
	}

	// Save new Employee into the DB
	public void saveEmployee(Employee e) {
		MyConnection.save(e);
	}

	// Update existing Employee in the DB
	public void updateEmployee(Employee e) {
		MyConnection.update(e);
	}

	// Delete Employee from the DB
	public void deleteEmployee(Employee e) {
		MyConnection.delete(e);
	}
}
