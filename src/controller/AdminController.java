package controller;

import java.util.List;

import model.Employee;
import dao.AdminDAO;

public class AdminController {
	AdminDAO adminDAO = new AdminDAO();

	public List<Employee> getListOfOwedPayment(String paymentStatus) {
		System.out.println("Payment status: " + paymentStatus);
		return adminDAO.getListOfOwedPayment(paymentStatus);
	}
}
