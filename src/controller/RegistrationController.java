package controller;

import java.util.Date;

import model.Company;
import model.Employee;
import services.PasswordService;
import dao.EmployeeDAO;

public class RegistrationController {

	public int registerUser(String username, String password, String name, long creditCardNo,
			long contactNo, String companyName) {

		Company company = new Company("abc", null, new Date(), null, null);
		String encryptPassword = PasswordService.encryptPassword(password);

		Employee newEmployee = new Employee(username, encryptPassword, name, creditCardNo, 10,
				contactNo, company, null, null, new Date());
		EmployeeDAO.saveEmployee(newEmployee);
		int id = newEmployee.getEmployeeId();

		return id;
	}

}