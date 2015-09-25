package controller;

import java.util.Date;
import java.util.Set;

import model.*;
import dao.*;
import services.PasswordService;

public class RegistrationController {
	
	public int registerUser(String username, String password, String name, String bankAcc,
			long contactNo, String companyName){
		Company company = new Company(companyName, null, new Date());
		String encryptPassword = PasswordService.encryptPassword(password);
		Employee newEmployee = new Employee(username, encryptPassword, name, bankAcc,
				contactNo, company, null,
				new Date());
		EmployeeDAO.saveEmployee(newEmployee);
		int id = newEmployee.getEmployeeId();
//		System.out.println(id + ", " + pwd);
		return id;
	}

}
