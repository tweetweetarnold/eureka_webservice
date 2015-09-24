package controller;

import model.*;
import dao.*;
import services.PasswordService;


public class LoginController {
	
	/*
	 * This method parses in user id and password for authentication.
	 * This method returns an Employee object upon a successful authentication,otherwise, it will return null
	 */
	public Employee authenticateUser (int id, String inputPassword)  {
//		String testUsername = "arnold";
//		String testPassword = "123";
		Employee e = null;
		 e = EmployeeDAO.getEmployee(id); 
		System.out.println("EMP" + e.getUsername());
		if (e != null) {
			int employeeid = e.getEmployeeId();
			if (employeeid != 0) {
				String employeePasswordinDB = e.getPassword();
				//String encryptedPwd = PasswordService.encryptPassword(employeePassword);
				//System.out.println(encryptedPwd);
				//e.setPassword(encryptedPwd);
				//EmployeeDAO.updateEmployee(e);
				if (employeePasswordinDB != null) {
					if (employeeid == id && inputPassword.equals(employeePasswordinDB)) {
						return e;
					} else {
						return null;
					}
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
//		return (testUsername.equals(username) && testPassword.equals(password));
	}

}
