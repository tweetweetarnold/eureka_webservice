package controller;

import java.util.List;

import dao.AdminDAO;
import dao.EmployeeDAO;
import dao.FoodOrderDAO;
import model.Employee;
import model.FoodOrder;

public class AdminController {

	
	public List<Employee> getListOfOwedPayment(String paymentStatus){
		System.out.println(paymentStatus);
		return AdminDAO.getListOfOwedPayment(paymentStatus);	
	}
}
