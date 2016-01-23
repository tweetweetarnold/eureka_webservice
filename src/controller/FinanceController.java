package controller;

import java.util.ArrayList;

import dao.EmployeeDAO;
import model.Employee;

/**
 * Performs the business logic of managing the finance for the web application
 * 
 * @author SMU Team Eureka
 * 
 */
public class FinanceController {
	EmployeeDAO employeeDAO = new EmployeeDAO();

	/**
	 * Creates a default constructor for FinanceController
	 */
	public FinanceController() {
	}

	/**
	 * Returns all users with outstanding payment that are above the specified amount(inclusive)
	 * 
	 * @param amount The specified amount
	 * @param inclusive Whether to include the specified amount
	 * @return An ArrayList of users with outstanding payment that are greater than (or equals) to
	 *         the specified amount
	 */
	public ArrayList<Employee> getAllUsersWithOutstandingPayment(double amount, boolean inclusive) {
		return employeeDAO.getAllUsersWithOutstandingPaymentAbove(amount, inclusive);
	}
}
