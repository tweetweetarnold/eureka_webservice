package controller;

import model.Admin;
import model.Employee;
import dao.AdminDAO;
import dao.EmployeeDAO;

public class LoginController {

	/*
	 * This method takes in userid and password for authentication. This method
	 * returns an Employee object upon a successful authentication, otherwise,
	 * it will return null
	 */
	public Employee authenticateUser(String inputUsername, String inputPassword) {

		Employee e = EmployeeDAO.getEmployeeByUsername(inputUsername);

		if (e != null) {
			String employeePasswordinDB = e.getPassword();
			// checking that the input password is correct as the password
			// stored in DataBase
			if (inputPassword.equals(employeePasswordinDB)) {
				return e;
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	public Admin authenticateAdmin(String inputUsername, String inputPassword) {
		Admin admin = AdminDAO.getAdminByUsername(inputUsername);
		if (admin != null) {
			String adminPasswordinDB = admin.getPassword();
			if (inputPassword.equals(adminPasswordinDB)) {
				return admin;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}
