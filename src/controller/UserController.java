package controller;

import model.Employee;
import dao.EmployeeDAO;

public class UserController {
	EmployeeDAO employeeDAO = new EmployeeDAO();

	public void updateEmployee(Employee e) {
		EmployeeDAO.updateEmployee(e);
		// EmployeeDAO.saveEmployee(e);

	}

	public void removeEmployee(Employee e) {
		EmployeeDAO.deleteEmployee(e);
	}

	public Employee retrieveEmployeeViaUsername(String username) {
		Employee employee = EmployeeDAO.getEmployeeByUsername(username);
		
		return employee;

	}

	// Employee ID not set yet so search by username
//	public Employee retrieveEmployeeTEMPORARY(String username) {
//		Employee tempE = employeeDAO.getEmployeeTEMPORARY(username);
//		return tempE;
//
//	}

}
