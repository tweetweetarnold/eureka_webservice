package controller;

import java.util.ArrayList;

import javax.mail.MessagingException;

import model.Admin;
import model.Company;
import model.Employee;

import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import services.AESAlgorithm;
import services.PasswordService;
import services.SendEmail;
import value.StringValues;
import dao.AdminDAO;

/**
 * Process the functions of user access control such as registration and logging in for
 * Administrator and normal user
 * 
 * @author SMU Team Eureka
 */
public class AccessController {

	AdminDAO adminDAO = new AdminDAO();
	AESAlgorithm aesAlgo = new AESAlgorithm();
	CompanyController companyController = new CompanyController();
	EmployeeController employeeController = new EmployeeController();

	/**
	 * Creates a default constructor for AccessController
	 */
	public AccessController() {
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
	 * Process the information provided by the user and verifies for a valid user
	 * 
	 * @param inputEmail The Email address provided by the user
	 * @param inputPassword The password provided by the user
	 * @return An Employee object upon successful verification, otherwise it returns null
	 */
	public Employee authenticateUser(String inputEmail, String inputPassword) {
		Employee emp = employeeController.getEmployeeByEmail(inputEmail);
		try {
			if (emp != null) {
				String password = emp.getPassword();
				/* checking that the input password is correct as the password stored in DataBase */
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
	 * Validates the acknowledgement of the Terms and Conditions
	 * 
	 * @param acknowledged The acknowledgement made by the Employee
	 * @return An ArrayList of error messages if the Terms and Conditions are not acknowledged
	 */
	public ArrayList<String> checkAcknowledgedTermsAndConditions(String acknowledged) {
		ArrayList<String> messages = new ArrayList<String>();
		if (acknowledged == null) {
			messages.add("Please acknowledge and agree to the Terms and Conditions.");
		}
		return messages;
	}
	
	public ArrayList<String> checkChangePasswordRequirements(Employee e, String oldPassword, String newPassword,
			String confirmNewPassword) {
		ArrayList<String> messages = new ArrayList<String>();
		boolean isNotCorrect = false;
		if (!e.getPassword().equals(encryptPassword(e.getEmail(), oldPassword))) {
			messages.add("Old password is invalid.");
			isNotCorrect = true;
		}
		ArrayList<String> errorMessages = checkPasswordMeetRequirements(newPassword,
				confirmNewPassword);
		
		if (!errorMessages.isEmpty()) {
			if (isNotCorrect) {
				errorMessages.add("Old password is invalid.");
			}
			return errorMessages;
		}
		return messages;
	}
	
	public ArrayList<String> checkCompanyCode(String companyCode) {
		ArrayList<String> messages = new ArrayList<String>();
		if (companyCode.equals("")) {
			messages.add("Company code cannot be empty.");
		} else {
			Company company = companyController.getCompanyByCompanyCode(companyCode);
			if (company == null) {
				messages.add("Failed to find company");
			}
		}
		return messages;
	}
	
	/**
	 * Validates the contact number for any violation of the following requirements: 
	 * <ul>
	 * <li>Contact number is less than 8 numbers
	 * <li>Contact number does not adhere to the standard numbering ranges
	 * </ul>
	 * 
	 * @param contactNo The contact number provided by the Employee
	 * @return An ArrayList of error messages if it fails the requirements
	 */
	public ArrayList<String> checkContactNoRequirements(String contactNo) {
		ArrayList<String> messages = new ArrayList<String>();
		if (!(contactNo.matches("[689][0-9][0-9][0-9][0-9][0-9][0-9][0-9]"))) {
			messages.add("Contact Number is not valid.");
		}
		return messages;
	}
	
	/**
	 * Validates the email address provided by the Employee
	 * 
	 * @param email The email address entered by the Employee
	 * @return An ArrayList of error messages in if it is not a valid email address
	 */
	public ArrayList<String> checkEmailRequirements(String email) {
		ArrayList<String> messages = new ArrayList<String>();
		if (!EmailValidator.getInstance().isValid(email)) {
			messages.add("Invalid Email. Please try again.");
		}
		EmployeeController employeeController = new EmployeeController();
		Employee tempEmployee = employeeController.retrieveEmployeeViaEmail(email);
		if(tempEmployee!=null){
			messages.add("Email in use. Please try again. If you forgot your password try resetting your password.");
		}
		return messages;
	}
	
	/**
	 * Validates the name of the Employee
	 * 
	 * @param name The name provided by the Employee
	 * @return An ArrayList of error messages if the name is empty
	 */
	public ArrayList<String> checkEmployeeNameRequirements(String name) {
		ArrayList<String> messages = new ArrayList<String>();
		if (name.equals("")) {
			messages.add("Employee name cannot be empty.");
		}
		return messages;
	}

	/**
	 * Validates the password for any violation of the following requirements:
	 * <ul>
	 * <li>Password is less than 7 characters
	 * <li>Password contains white spaces characters
	 * <li>Password and Confirm Password inputs does not match
	 * </ul>
	 * 
	 * @param password The password entered by the Employee
	 * @param confirmPwd The password for confirming
	 * @return An ArrayList of error messages if fails the requirements
	 */
	public ArrayList<String> checkPasswordMeetRequirements(String password, String confirmPwd) {
		ArrayList<String> messages = new ArrayList<String>();
		if (!password.equals(confirmPwd)) {
			messages.add("Passwords do not match.");
		}
		//in the case where password is less than 7
		if (!(password.length() >= 7)) {
			messages.add("Password must be at least 7 characters long.");
			//after validating that it is less than 7, check for empty spaces
			if (password.contains(" ")) {
				messages.add("Password should not contain empty spaces.");
			}
			
		} else { 
			// in the case where password is greater than 7, it will proceed to valid for empty spaces
			if (password.contains(" ")) {
				messages.add("Password should not contain empty spaces.");
			}
		}
		return messages;
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
			String confirmPwd, String contactNo, String acknowledged, String companyCode) {
		ArrayList<String> messages = new ArrayList<String>();

		messages.addAll(checkEmailRequirements(email));
		messages.addAll(checkEmployeeNameRequirements(name));
		messages.addAll(checkContactNoRequirements(contactNo));
		messages.addAll(checkPasswordMeetRequirements(password, confirmPwd));
		messages.addAll(checkAcknowledgedTermsAndConditions(acknowledged));
		messages.addAll(checkCompanyCode(companyCode));

		if (!messages.isEmpty()) {
			return messages;
		}
		return null;

	}

	/**
	 * Constructs an email message for Resetting password
	 * 
	 * @param serverName The name of the Server
	 * @param serverPort The Port number of the server
	 * @param contextPath The root path or URL of the application
	 * @param email The designated of the Employee's email address
	 * @param toSendEmail The designated recipients of this email message
	 * @return Returns true when the email message has constructed and successfully sent
	 * @throws MessagingException if the email message could not be constructed or sent
	 */
	public boolean constructResetPasswordEmail(String serverName, int serverPort,
			String contextPath, String email, String[] toSendEmail) throws MessagingException {
		SendEmail javaEmail = new SendEmail();
		AESAlgorithm aes = new AESAlgorithm();

		String appUrl = "http://" + serverName + ":" + serverPort + contextPath;
		
		DateTimeZone.setDefault(DateTimeZone.forID("Asia/Singapore"));
		System.out.println("Controller TIME ZONE: " + DateTimeZone.getDefault().toString());
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMMM-yyyy HH:mm");
		DateTime currentTime = new DateTime(DateTimeZone.forID("Asia/Singapore"));
		System.out.println("NOW: " + currentTime);
		
		String time = aes.encrypt(formatter.print(currentTime));
		String token = time;
		String eEncrypt = aes.encrypt(email);
		String url = appUrl + "/LoadResetPasswordPage?email=" + eEncrypt + "&token=" + token;

		javaEmail.setMailServerProperties();
		javaEmail.sendEmail("Koh Bus LunchTime Ordering App - Password Reset", "Dear User,<br><br>"
				+ "To reset your password for LunchTime Ordering App, please on click the following link <b>within 5 minutes</b>:<br> "
				+ "<a href=" + url + ">" + url + "</a>" + "<br><br>" + "Regards,<br>"
				+ "Admin<br><br>"
				+ "This is a system-generated email; please DO NOT REPLY to this email.<br>",
				toSendEmail);

		return true;
	}
	
	public boolean constructVerifyEmail(String serverName, int serverPort,
			String contextPath, String email, String[] toSendEmail) throws MessagingException {
		SendEmail javaEmail = new SendEmail();
		
		String appUrl = "http://" + serverName + ":" + serverPort + contextPath;
		
		DateTimeZone.setDefault(DateTimeZone.forID("Asia/Singapore"));
		System.out.println("Controller TIME ZONE: " + DateTimeZone.getDefault().toString());
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMMM-yyyy HH:mm");
		DateTime currentTime = new DateTime(DateTimeZone.forID("Asia/Singapore"));
		System.out.println("NOW: " + currentTime);
		
		String time = aesAlgo.encrypt(formatter.print(currentTime));
		String token = time;
		String eEncrypt = aesAlgo.encrypt(email);
		String encryptedStatus = aesAlgo.encrypt(StringValues.EMPLOYEE_OK);
		
		
		String url = appUrl + "/ProcessVerificationServlet?email="
				+ eEncrypt + "&status=" + encryptedStatus + "&token=" + token;
		
		javaEmail.setMailServerProperties();
		javaEmail
		.sendEmail(
				"Koh Bus LunchTime Ordering App - Verify Your Email",
				"Dear User,<br><br>"
						+ "Welcome to LunchTime Ordering App, please click the following link <b>within 5 minutes</b> to verify your email address:<br><br> "
						+ "<a href="
						+ url
						+ ">"
						+ url
						+ "</a>"
						+ "<br><br>"
						+ "Regards,<br>"
						+ "Admin<br><br>"
						+ "This is a system-generated email; please DO NOT REPLY to this email.<br>",
				toSendEmail);
		
		return true;
		
	}

	/**
	 * Performs AES(Advanced Encryption Standard) encryption for the Employee's password
	 * 
	 * @param email The email address of the Employee
	 * @param password The password of the Employee in plain text
	 * @return The encrypted password
	 */
	private String encryptPassword(String email, String password) {
		return aesAlgo.encrypt(email + password);
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
			String companyCode, String deliveryPoint) throws Exception {
		String encryptPassword = encryptPassword(email, password);
		Company company = null;

		try {
			company = companyController.getCompanyByCompanyCode(companyCode);
			
		} catch (Exception exception) {
			throw new Exception("Failed to find company");
		}

		Employee newEmployee = new Employee(encryptPassword, name, email, contactNo, company);
		newEmployee.setDeliveryPoint(deliveryPoint);
		newEmployee.setStatus(StringValues.EMPLOYEE_PENDING_VERIFICATION);
		try {
			employeeController.saveEmployee(newEmployee);
		} catch (ConstraintViolationException e) {
			throw new Exception("Email already exists! Please log in with that email.");
		}
		return newEmployee.getEmail();
	}

	/**
	 * Update the current contact number of the Employee in the database
	 * 
	 * @param e The designated Employee to be updated
	 * @param newContactNumber The new contact number provided by the Employee
	 * @return Returns true when the contact number has been updated
	 * @throws Exception if the new contact number of the Employee could not be updated in the database
	 */
	public boolean updateEmployeeContactNumber(Employee e, String newContactNumber) throws Exception {
		long newContactNum = Long.parseLong(newContactNumber);
		e.setContactNo(newContactNum);
		employeeController.updateEmployee(e);
		
		return true;
	}
	
	//used in ProcessChangePasswordServlet
	/**
	 * Updates the current password of the Employee in the database
	 * 
	 * @param e The designated Employee to be updated
	 * @param password The new password provided by the Employee
	 * @return Returns true when the password has been updated
	 * @throws Exception if the new password of the Employee could not be updated in the database
	 */
	public boolean updateEmployeePassword(Employee e, String password) throws Exception {
		e.setPassword(encryptPassword(e.getEmail(), password));
		employeeController.updateEmployee(e);
		return true;
	}
	
	/**
	 * Updates the current password of the Employee in the database
	 * 
	 * @param e The designated Employee to be updated
	 * @param oldPassword The Employee's current password
	 * @param newPassword The Employee's new password
	 * @param confirmNewPassword The Employee's new password for confirmation
	 * @return Returns true when the password has been updated
	 * @throws Exception if the new password of the Employee could not be updated in the database
	 */
	public boolean updateEmployeePassword(Employee e, String oldPassword, String newPassword,
			String confirmNewPassword) throws Exception {

			ArrayList<String> errorMessages = checkChangePasswordRequirements(e, oldPassword, newPassword,
					confirmNewPassword);
			if (!errorMessages.isEmpty()) {
				String msg = "";
				for (String s : errorMessages) {
					msg = s + "\n" + msg;
				}
				throw new Exception(msg);

				
			}
			e.setPassword(encryptPassword(e.getEmail(), newPassword));
			employeeController.updateEmployee(e);
			return true;
		
		
	}

}
