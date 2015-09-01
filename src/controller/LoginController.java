package controller;

import dao.*;
import entity.*;

public class LoginController {
	
	public boolean authenticateUser(String username, String password) {
		String testUsername = "arnold";
		String testPassword = "123";
		
		//BH modified the authenticating codes for the case of employee login
		//invoking the new getEmployeUsername method
		Employee e = EmployeeDAO.getEmployeeUsername(username); 
		if (e != null) {
			String employeeUsername = e.getUsername();
			if (employeeUsername != null) {
				String employeePassword = e.getPassword();
				if (employeePassword != null) {
					return (employeeUsername.equals(username) && employeePassword.equals(password));
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
		
		
		//return (testUsername.equals(username) && testPassword.equals(password));
	}

}
