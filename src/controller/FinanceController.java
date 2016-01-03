package controller;

import java.util.ArrayList;

import dao.EmployeeDAO;
import model.Employee;

public class FinanceController {
	EmployeeDAO employeeDAO = new EmployeeDAO();

	public ArrayList<Employee> getAllUsersWithOutstandingPayment(double amount, boolean inclusive) {
		return employeeDAO.getAllUsersWithOutstandingPaymentAbove(amount, inclusive);
	}
}
