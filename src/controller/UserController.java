package controller;

import dao.EmployeeDAO;
import entity.Employee;
public class UserController {
	EmployeeDAO employeeDAO = new EmployeeDAO();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}
	
	public Employee retrieveEmployee(int id){
		Employee tempE = employeeDAO.getEmployee(id);
		return tempE;

	}
	//Employee ID not set yet so search by username
	public Employee retrieveEmployeeTEMPORARY(String username){
		Employee tempE = employeeDAO.getEmployeeTEMPORARY(username);
		return tempE;

	}
}
