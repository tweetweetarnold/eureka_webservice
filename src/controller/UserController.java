package controller;

import model.Employee;

import java.util.List;

import dao.EmployeeDAO;

public class UserController {
	EmployeeDAO employeeDAO = new EmployeeDAO();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void updateEmployee(Employee e) {
		EmployeeDAO.updateEmployee(e);
		// EmployeeDAO.saveEmployee(e);

	}

	public void removeEmployee(Employee e) {
		EmployeeDAO.deleteEmployee(e);
	}

	public Employee retrieveEmployeeViaUsername(String username) {
		List<Employee> employeeList = EmployeeDAO.getEmployeeByUsername(username);
		for (Employee e : employeeList) {
			if (e.getUsername().equals(username)) {
				return e;
			}
		}
		return null;

	}

	// Employee ID not set yet so search by username
//	public Employee retrieveEmployeeTEMPORARY(String username) {
//		Employee tempE = employeeDAO.getEmployeeTEMPORARY(username);
//		return tempE;
//
//	}

}
