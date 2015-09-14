package controller;

import dao.*;
import entity.*;
import is203.JWTUtility;
import is203.JWTException;

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
	
	/*
	 * This method parses in username and password inputs and perform authenticating. 
	 * If username and password are valid,It will generate and returns a String object.
	 */
	public String tokenGenerator (String username, String password) {
		
		Employee e = EmployeeDAO.getEmployeeUsername(username); 
		if (e != null) {
			String employeeUsername = e.getUsername();
			String employeePassword = e.getPassword();
			if (employeeUsername != null && employeePassword != null) {
				if (employeeUsername.equals(username) && employeePassword.equals(password)) {
					//String token generating process
					String jwt = JWTUtility.sign("asdfghjklmnbvcxz", username);
	                String strVerify = null;
	                try {
	                	strVerify = JWTUtility.verify(jwt,"asdfghjklmnbvcxz" );
		                if (strVerify.equals(username)) {
		                	return jwt;
		                }
	                } catch (JWTException ex) {
	                	String s = ex.getMessage();
	                    return null;
	                }
				} else {
					return null;
				}
					//return (employeeUsername.equals(username) && employeePassword.equals(password));
			} else {
				return null;
			}
		} else {
			return null;
		}
		return null;
	}

}
