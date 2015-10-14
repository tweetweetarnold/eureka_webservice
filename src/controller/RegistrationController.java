package controller;

import java.util.Date;

import model.Company;
import model.Employee;
import services.PasswordService;
import dao.EmployeeDAO;

public class RegistrationController {

	public String registerUser(String username, String password, String name, String email,
			long contactNo, String companyCode) {

		String encryptPassword = PasswordService.encryptPassword(password);
		Company company = CompanyController.getCompanyByCompanyCode(companyCode);

		Employee newEmployee = new Employee(username, encryptPassword, name, email, contactNo,
				company, new Date());
		EmployeeDAO.saveEmployee(newEmployee);
		String id = newEmployee.getUsername();

		return id;
	}

}
