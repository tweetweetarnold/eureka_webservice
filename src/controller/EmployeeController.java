package controller;

import model.Employee;
import dao.EmployeeDAO;

/**
 * Process the function of managing Employee's information
 * 
 * @author SMU Team Eureka
 * 
 */
public class EmployeeController {
	EmployeeDAO employeeDAO = new EmployeeDAO();

	/**
	 * Creates a default constructor for EmployeeController
	 */
	public EmployeeController() {
	}

	/**
	 * Retrieve the Employee's specified food delivery point
	 * 
	 * @param email The email address of the Employee
	 * @return The specified food delivery point
	 */
	public String getDefaultDeliveryPoint(String email) {
		Employee employee = retrieveEmployeeViaEmail(email);
		return employee.getDeliveryPoint();
	}

	/**
	 * Retrieves the Employee based on the provided email address
	 * 
	 * @param email The email address of the employee
	 * @return The Employee object of the email address, otherwise returns null
	 */
	public Employee getEmployeeByEmail(String email) {
		return employeeDAO.getEmployeeByEmail(email);
	}

	/**
	 * Removes the designated Employee object from the Database
	 * 
	 * @param e The Employee object to be removed
	 */
	public void removeEmployee(Employee e) {
		employeeDAO.deleteEmployee(e);
	}

	/**
	 * Retrieves the Employee based on the provided email address
	 * 
	 * @param email The email address of the employee
	 * @return The Employee object of the email address
	 */
	public Employee retrieveEmployeeViaEmail(String email) {
		return employeeDAO.getEmployeeByEmail(email);
	}

	/**
	 * Adds a new Employee object to the Database
	 * 
	 * @param e The Employee object to be added in
	 */
	public void saveEmployee(Employee e) {
		employeeDAO.saveEmployee(e);
	}

	/**
	 * Updates the Employee's specified food delivery point
	 * 
	 * @param email The email address of the Employee
	 * @param buildingName The name of the building for the new delivery point
	 */
	public void updateDefaultDeliveryPoint(String email, String buildingName) {
		Employee employee = retrieveEmployeeViaEmail(email);
		employee.setDeliveryPoint(buildingName);
		employeeDAO.updateEmployee(employee);
	}

	/**
	 * Updates the designated Employee object in the Database
	 * 
	 * @param e The Employee object to be updated
	 */
	public void updateEmployee(Employee e) {
		employeeDAO.updateEmployee(e);
	}
}
