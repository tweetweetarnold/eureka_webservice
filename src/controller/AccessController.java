package controller;

import org.hibernate.exception.ConstraintViolationException;

import services.PasswordService;
import model.Admin;
import model.Company;
import model.Employee;
import dao.AdminDAO;
import dao.EmployeeDAO;

public class AccessController {
	EmployeeDAO employeeDAO = new EmployeeDAO();
	AdminDAO adminDAO = new AdminDAO();

	/*
	 * This method takes in userid and password for authentication. This method
	 * returns an Employee object upon a successful authentication, otherwise,
	 * it will return null
	 */
	public Employee authenticateUser(String inputEmail, String inputPassword) {
		Employee e = employeeDAO.getEmployeeByEmail(inputEmail);
		if (e != null) {
			String employeePasswordinDB = e.getPassword();
			// checking that the input password is correct as the password
			// stored in DataBase
			if (inputPassword.equals(employeePasswordinDB)) {
				return e;
			}
		}
		return null;
	}

	public Admin authenticateAdmin(String inputUsername, String inputPassword) {
		Admin admin = adminDAO.getAdminByUsername(inputUsername);
		if (admin != null) {
			String adminPasswordinDB = admin.getPassword();
			if (inputPassword.equals(adminPasswordinDB)) {
				return admin;
			}
		}
		return null;
	}

	public String registerUser(String password, String name, String email, long contactNo,
			String companyCode) throws Exception {

		CompanyController companyController = new CompanyController();

		String encryptPassword = PasswordService.encryptPassword(password);
		Company company = null;

		try {
			company = companyController.getCompanyByCompanyCode(companyCode);
		} catch (Exception exception) {
			throw new Exception("Failed to find company");
		}

		Employee newEmployee = new Employee(encryptPassword, name, email, contactNo, company);
		try {
			employeeDAO.saveEmployee(newEmployee);
		} catch (ConstraintViolationException e) {
			throw new Exception("Username already exists! Please choose another username.");
		}
		String id = newEmployee.getEmail();

		return id;
	}

	public String registerAdmin(String username, String password, String name, long contactNo) {
		String encryptPassword = PasswordService.encryptPassword(password);

		Admin newAdmin = new Admin(username, encryptPassword, name, contactNo);
		adminDAO.saveAdmin(newAdmin);
		String id = newAdmin.getUsername();

		return id;
	}

}
