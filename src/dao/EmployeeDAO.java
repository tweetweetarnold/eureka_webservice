package dao;

import java.util.ArrayList;
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

	// Retrieve Employee from DB using Employee username
	public Employee getEmployeeByEmail(String email) {
		DetachedCriteria dc = DetachedCriteria.forClass(Employee.class);
		dc.add(Restrictions.eq("email", email));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);
		if (l.size() == 0) {
			return null;
		}
		return (Employee) l.get(0);
	}

	public ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> returnList = null;

		DetachedCriteria dc = DetachedCriteria.forClass(Employee.class);
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		returnList = new ArrayList<Employee>();

		for (Object o : l) {
			returnList.add((Employee) o);
		}
		return returnList;
	}
}
