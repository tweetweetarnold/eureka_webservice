package controller;

import model.Employee;
import dao.EmployeeDAO;

/**
 * Process the function of managing Employee's information
 * 
 * 
 */
public class EmployeeController {
	EmployeeDAO employeeDAO = new EmployeeDAO();

	/**
	 * Creates a default constructor for EmployeeController
	 */
	public EmployeeController() {
	}

	public void saveEmployee(Employee e) {
		employeeDAO.saveEmployee(e);
	}

	public Employee getEmployeeByEmail(String email) {
		return employeeDAO.getEmployeeByEmail(email);
	}

	/**
	 * Updates the designated Employee object in the database
	 * 
	 * @param e The Employee object to be updated
	 */
	public void updateEmployee(Employee e) {
		employeeDAO.updateEmployee(e);
	}

	/**
	 * Removes the designated Employee object from the database
	 * 
	 * @param e The Employee object to be removed
	 */
	public void removeEmployee(Employee e) {
		employeeDAO.deleteEmployee(e);
	}

	/**
	 * Updates the Employee's specified food delivery point
	 * 
	 * @param email The email address of the Employee
	 * @param buildingName The name of the building for the new delivery point
	 */
	public void updateDefaultDeliveryPoint(String email, String buildingName) {
		Employee employee = retrieveEmployeeViaEmail(email);
		employee.setDefaultDeliveryPoint(buildingName);
		employeeDAO.updateEmployee(employee);
	}

	/**
	 * Retrieve the Employee's specified food delivery point
	 * 
	 * @param email The email address of the Employee
	 * @return The specified food delivery point
	 */
	public String getDefaultDeliveryPoint(String email) {
		Employee employee = retrieveEmployeeViaEmail(email);
		return employee.getDefaultDeliveryPoint();
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
}
