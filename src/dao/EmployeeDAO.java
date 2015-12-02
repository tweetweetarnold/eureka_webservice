package dao;

import java.util.List;

import model.Employee;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import connection.MyConnection;

public class EmployeeDAO {

	public EmployeeDAO() {
	}

	// Retrieve Employee from DB with EmployeeID
	public static Employee getEmployee(int employeeId) {
		return (Employee) MyConnection.get(Employee.class, employeeId);
	}

	// Retrieve Employee from DB using Employee username
	public static Employee getEmployeeByUsername(String username) {
		// String sqlQuery = "SELECT * FROM  employee where username=\"" +
		// username + "\"";
		// List<Object> lister = MyConnection.getEmployee(sqlQuery);
		//
		// for (Object o : lister) {
		// Employee employee = (Employee) o;
		// if (employee.getUsername().equals(username)) {
		// return employee;
		// }
		//
		// }
		// return null;

		DetachedCriteria dc = DetachedCriteria.forClass(Employee.class);
		dc.add(Restrictions.eq("username", username));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		List<Object> l = MyConnection.queryWithCriteria(dc);

		return (Employee) l.get(0);
	}
	
	

	// Save new Employee into the DB
	public static void saveEmployee(Employee e) {
		MyConnection.save(e);
	}

	// Update existing Employee in the DB
	public static void updateEmployee(Employee e) {
		MyConnection.update(e);
	}

	// Delete Employee from the DB
	public static void deleteEmployee(Employee e) {
		MyConnection.delete(e);
	}
}
