package controller;

import dao.*;
import entity.*;

import services.PasswordService;


public class LoginController {
	
	public Employee authenticateUser (int id, String password)  {
//		String testUsername = "arnold";
//		String testPassword = "123";
		Employee e = null;
		//BH modified the authenticating codes for the case of employee login
		//invoking the new getEmployeUsername method
		 e = EmployeeDAO.getEmployee(id); 
		System.out.println("EMP" + e.getUsername());
		if (e != null) {
			int employeeid = e.getEmployeeId();
			if (employeeid != 0) {
				String employeePassword = e.getPassword();
				//String encryptedPwd = PasswordService.encryptPassword(employeePassword);
				//System.out.println(encryptedPwd);
				//e.setPassword(encryptedPwd);
				//EmployeeDAO.updateEmployee(e);
				if (employeePassword != null) {
					if (employeeid == id && employeePassword.equals(employeePassword)) {
						return e;
					} else {
						return e;
					}
				} else {
					return e;
				}
			} else {
				return e;
			}
		} else {
			return e;
		}
//		return (testUsername.equals(username) && testPassword.equals(password));
	}
	
	
	
	
	/*
	 * This method parses in username and password inputs and perform authenticating. 
	 * If username and password are valid,It will generate and returns a String object.
	 */
//	public String tokenGenerator (String username, String password) {
//		
//		Employee e = EmployeeDAO.getEmployeeUsername(username); 
//		if (e != null) {
//			String employeeUsername = e.getUsername();
//			String employeePassword = e.getPassword();
//			if (employeeUsername != null && employeePassword != null) {
//				if (employeeUsername.equals(username) && employeePassword.equals(password)) {
//					//String token generating process
//					String jwt = JWTUtility.sign("asdfghjklmnbvcxz", username);
//	                String strVerify = null;
//	                try {
//	                	strVerify = JWTUtility.verify(jwt,"asdfghjklmnbvcxz" );
//		                if (strVerify.equals(username)) {
//		                	return jwt;
//		                }
//	                } catch (JWTException ex) {
//	                	String s = ex.getMessage();
//	                    return null;
//	                }
//				} else {
//					return null;
//				}
//					//return (employeeUsername.equals(username) && employeePassword.equals(password));
//			} else {
//				return null;
//			}
//		} else {
//			return null;
//		}
//		return null;
//	}

}
