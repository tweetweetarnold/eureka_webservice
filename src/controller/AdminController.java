package controller;

import java.util.List;

import model.Employee;
import dao.AdminDAO;

/**
 * Process the business logic required by an Administrator
 * 
 * @author SMU Team Eureka
 *
 */
public class AdminController {
	AdminDAO adminDAO = new AdminDAO();
	
	/**
	 * Creates a default constructor for AdminController
	 */
	public AdminController() {
		
	}
	
	/**
	 * Retrieve a list of Employees whom owes payment
	 * 
	 * @param paymentStatus The payment status of the Employees
	 * @return A list of Employees whom status are indicated as "Owed"
	 */
	public List<Employee> getListOfOwedPayment(String paymentStatus) {
		System.out.println("Payment status: " + paymentStatus);
		return adminDAO.getListOfOwedPayment(paymentStatus);
	}
}
