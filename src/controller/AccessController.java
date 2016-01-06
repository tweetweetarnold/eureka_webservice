package controller;

import java.util.ArrayList;

import model.Admin;
import model.Company;
import model.Employee;

import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.exception.ConstraintViolationException;

import services.AESAlgorithm;
import services.PasswordService;
import value.StringValues;
import dao.AdminDAO;
import dao.EmployeeDAO;

/**
 * Process the functions of user access control such as registration and logging in for
 * Administrator and normal user
 * 
 * 
 */
public class AccessController {

	EmployeeDAO employeeDAO = new EmployeeDAO();
	AdminDAO adminDAO = new AdminDAO();
	CompanyController companyController = new CompanyController();
	AESAlgorithm aesAlgo = new AESAlgorithm();

	/**
	 * Creates a default constructor for AccessController
	 */
	public AccessController() {
	}

	/**
	 * Checks the user parameters for registration to see if they meet the requirements.
	 * 
	 * @param email The email provided.
	 * @param name The name provided.
	 * @param password The password provided.
	 * @param confirmPwd The confirm password provided.
	 * @param contactNo The contact number provided.
	 * @return An ArrayList of error messages (Strings) is returned. If no error messages, returns
	 *         null.
	 */
	public ArrayList<String> checkUserInputs(String email, String name, String password,
			String confirmPwd, String contactNo) {
		ArrayList<String> messages = new ArrayList<String>();

		messages.addAll(checkEmailRequirements(email));
		messages.addAll(checkEmployeeNameRequirements(name));
		messages.addAll(checkContactNoRequirements(contactNo));
		messages.addAll(checkPasswordMeetRequirements(password, confirmPwd));

		if (!messages.isEmpty()) {
			return messages;
		}
		return null;
	}

	public ArrayList<String> checkEmailRequirements(String email) {
		ArrayList<String> messages = new ArrayList<String>();
		if (!EmailValidator.getInstance().isValid(email)) {
			messages.add("Invalid Email. Please try again.");
		}
		return messages;
	}

	public ArrayList<String> checkEmployeeNameRequirements(String name) {
		ArrayList<String> messages = new ArrayList<String>();
		if (name.equals("")) {
			messages.add("Employee name cannot be empty.");
		}
		return messages;
	}

	public ArrayList<String> checkPasswordMeetRequirements(String password, String confirmPwd) {
		ArrayList<String> messages = new ArrayList<String>();
		if (!password.equals(confirmPwd)) {
			messages.add("Passwords do not match.");
		}
		if (!(password.length() >= 7) && password.contains(" ")) {
			messages.add("Password must be at least 7 characters long without spaces.");
		}
		return messages;
	}

	public ArrayList<String> checkContactNoRequirements(String contactNo) {
		ArrayList<String> messages = new ArrayList<String>();
		if (contactNo.length() != 8
				&& !(contactNo.matches("[689][0-9][0-9][0-9][0-9][0-9][0-9][0-9]"))) {
			messages.add("Contact Number is not valid.");
		}
		return messages;
	}

	/**
	 * Process the information provided by the user and verifies for a valid user
	 * 
	 * @param inputEmail The Email address provided by the user
	 * @param inputPassword The password provided by the user
	 * @return An Employee object upon successful verification, otherwise it returns null
	 */
	public Employee authenticateUser(String inputEmail, String inputPassword) {
		Employee emp = employeeDAO.getEmployeeByEmail(inputEmail);
		try {
			if (emp != null) {
				String password = emp.getPassword();
				// checking that the input password is correct as the password
				// stored in DataBase
				if (aesAlgo.encrypt(inputEmail + inputPassword).equals(password)) {
					return emp;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Process the information provided by the user and verifies for a valid Administrator
	 * 
	 * @param inputUsername The username of the Administrator
	 * @param inputPassword The password of the Administrator
	 * @return An Admin object upon successful verification, otherwise it returns null
	 */
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

	/**
	 * Registers a new user to gain access to the application
	 * 
	 * @param password The password of the user
	 * @param name The name of the user
	 * @param email The Email Address of the user
	 * @param contactNo The contact number of the user
	 * @param companyCode The company code which is assigned to the user
	 * @return The email address of the user which is needed for verification upon user access
	 * @throws Exception If it fails to locate the Company object based on the company code or an
	 *             Email Address already exists
	 */
	public String registerUser(String password, String name, String email, long contactNo,
			String companyCode) throws Exception {
		String encryptPassword = aesAlgo.encrypt(email + password);
		Company company = null;

		try {
			company = companyController.getCompanyByCompanyCode(companyCode);
		} catch (Exception exception) {
			throw new Exception("Failed to find company");
		}

		Employee newEmployee = new Employee(encryptPassword, name, email, contactNo, company);
		newEmployee.setStatus(StringValues.EMPLOYEE_PENDING_VERIFICATION);
		try {
			employeeDAO.saveEmployee(newEmployee);
		} catch (ConstraintViolationException e) {
			throw new Exception("Email already exists! Please log in with that email.");
		}
		return newEmployee.getEmail();
	}

	/**
	 * Registers a new Administrator to gain access to classified information
	 * 
	 * @param username The username of the Administrator
	 * @param password The password of the Administrator
	 * @param name The Name of the Administrator
	 * @param contactNo The contact number of the Administrator
	 * @return The username of the Administrator which is needed for verification upon logging in
	 *         upon a successful registration
	 */
	public String registerAdmin(String username, String password, String name, long contactNo) {
		String encryptPassword = PasswordService.encryptPassword(password);

		Admin newAdmin = new Admin(username, encryptPassword, name, contactNo);
		adminDAO.saveAdmin(newAdmin);

		return newAdmin.getUsername();
	}

}
