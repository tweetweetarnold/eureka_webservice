package controller;

import java.util.List;

import model.Employee;
import dao.AdminDAO;

public class AdminController {

	
	public List<Employee> getListOfOwedPayment(String paymentStatus){
		System.out.println(paymentStatus);
		return AdminDAO.getListOfOwedPayment(paymentStatus);	
	}
}
