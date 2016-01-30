package dao;

import java.util.ArrayList;
import java.util.List;

import model.Employee;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import connection.MyConnection;

/**
 * Performs the function of Data Access Object for the Employee model
 * 
 * @author SMU Team Eureka
 */
public class EmployeeDAO {

	/**
	 * Creates a default constructor for EmployeeDAO
	 */
	public EmployeeDAO() {

	}

	/**
	 * Removes the designated Employee object from the Database
	 * 
	 * @param e The Employee object to be removed
	 */
	public void deleteEmployee(Employee e) {
		MyConnection.delete(e);
	}

	/**
	 * Retrieves all the Employees from the Database
	 * 
	 * @return An ArrayList of Employee objects
	 */
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

	/**
	 * Retrieves all users with outstanding payment that are above the specified amount(inclusive)
	 * 
	 * @param amount The specified amount
	 * @param inclusive Whether to include the specified amount
	 * @return An ArrayList of users with outstanding payment that are greater than (or equals) to the specified amount
	 */
	public ArrayList<Employee> getAllUsersWithOutstandingPaymentAbove(double amount,
			boolean inclusive) {
		ArrayList<Employee> returnList = null;

		DetachedCriteria dc = DetachedCriteria.forClass(Employee.class);
		if (inclusive) {
			dc.add(Restrictions.ge("amountOwed", amount));
		} else {
			dc.add(Restrictions.gt("amountOwed", amount));
		}

		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		returnList = new ArrayList<Employee>();

		for (Object o : l) {
			returnList.add((Employee) o);
		}
		return returnList;
	}

	/**
	 * Retrieves the Employee based on the provided ID
	 * 
	 * @param employeeId The ID used for retrieving the Employee
	 * @return The Employee object that has the provided ID
	 */
	public Employee getEmployee(int employeeId) {
		return (Employee) MyConnection.get(Employee.class, employeeId);
	}

	/**
	 * Retrieves the Employee based on the provided email address
	 * 
	 * @param email The email address of the employee
	 * @return The Employee object of the email address, otherwise returns null
	 */
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

	/**
	 * Adds a new Employee object to the Database
	 * 
	 * @param e The Employee object to be added in
	 */
	public void saveEmployee(Employee e) {
		MyConnection.save(e);
	}
	
	/**
	 * Updates the designated Employee object in the Database
	 * 
	 * @param e The Employee object to be updated
	 */
	public void updateEmployee(Employee e) {
		MyConnection.update(e);
	}
}
