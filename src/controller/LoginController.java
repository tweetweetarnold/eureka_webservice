package controller;

import model.*;

import java.util.UUID;

import dao.*;
import services.PasswordService;


public class LoginController {
	
	/*
	 * This method takes in userid and password for authentication.
	 * This method returns an Employee object upon a successful authentication, otherwise, it will return null
	 */
	public Employee authenticateUser (int id, String inputPassword)  {

		Employee e = null;
		e = EmployeeDAO.getEmployee(id);
		
		int employeeid = e.getEmployeeId();
		if (employeeid != 0) {
			String employeePasswordinDB = e.getPassword();
			//checking that the input password is correct as the password stored in DataBase
			if (inputPassword.equals(employeePasswordinDB)) {
				return e;
			} else {
				return null;
			}
		} else {
			return null;
		}
		
	}

}
