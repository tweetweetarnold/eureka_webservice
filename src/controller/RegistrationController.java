package controller;

import java.util.Date;

import model.Admin;
import model.Company;
import model.Employee;
import services.PasswordService;
import dao.AdminDAO;
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
	
	public String registerAdmin(String username, String password, String name, 
			long contactNo) {

		String encryptPassword = PasswordService.encryptPassword(password);
		
		Admin newAdmin = new Admin(username, encryptPassword, name, contactNo, new Date());
		AdminDAO.saveadmin(newAdmin);
		String id = newAdmin.getUsername();

		return id;
	}

}
