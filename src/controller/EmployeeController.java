package controller;

import value.StringValues;
import java.util.ArrayList;

import javax.mail.MessagingException;

import connection.MyConnection;
import model.Company;
import model.Employee;
import services.SendEmail;
import dao.EmployeeDAO;

/**
 * Process the function of managing Employee's information
 * 
 * @author SMU Team Eureka
 * 
 */
public class EmployeeController {
	EmployeeDAO employeeDAO = new EmployeeDAO();

	/**
	 * Creates a default constructor for EmployeeController
	 */
	public EmployeeController() {
	}
	
	/**
	 * retrieves all employees from the database with status of "destroyed" 
	 * @return ArrayList of Employees
	 */
	public ArrayList<Employee> getAllDestroyedEmployees() {
		return employeeDAO.getAllDestroyedEmployees();
	}
	/**
	 * retrieves all employees from the database with status of "active" 
	 * @return ArrayList of Employees
	 */
	public ArrayList<Employee> getAllNonDestroyedEmployees() {
		return employeeDAO.getAllNonDestroyedEmployees();
	}

	/**
	 * Retrieve the Employee's specified food delivery point
	 * 
	 * @param email The email address of the Employee
	 * @return The specified food delivery point
	 */
	public String getDefaultDeliveryPoint(String email) {
		return retrieveEmployeeViaEmail(email).getDeliveryPoint();
	}

	/**
	 * Retrieves the Employee based on the provided email address
	 * 
	 * @param email The email address of the employee
	 * @return The Employee object of the email address, otherwise returns null
	 */
	public Employee getEmployeeByEmail(String email) {
		return employeeDAO.getEmployeeByEmail(email);
	}
	
	
	/**
	 * This sends an email to all employees with the delivery point to notify them to update their delivery point in the event that their old delivery point is deleted
	 * @param c the company to notify
	 * @param deliveryPoint the delivery point deleted
	 */
	public void notifyAllEmployeesFromCompanyWithDeliveryPoint(Company c, String deliveryPoint) {
		ArrayList<Employee> results = employeeDAO.getAllEmployeesFromCompanyWithDeliveryPoint(c,
				deliveryPoint);

		ArrayList<String> emailList = new ArrayList<String>();

		SendEmail emailGen = new SendEmail();

		String url = "http://lunchtime.dal.jelastic.vps-host.net/eureka_webservice/pages/login.jsp";
		String subject = "Koh Bus LunchTime Ordering App - Please Update Delivery Building";
		String messageBody = "Dear User,<br><br>"
				+ "Please note that your chosen delivery building is no longer valid please specify a new building.<br><br>"
				+ "You can select a new building from the edit profile page after you login. <br><br>"
				+ "<a href=" + url + ">" + url + "</a>" + "<br><br>" + "Regards,<br>"
				+ "Admin<br><br>"
				+ "This is a system-generated email; please DO NOT REPLY to this email.<br>";

		for (Employee e : results) {
			String tempEmail = e.getEmail();
			emailList.add(tempEmail);
		}
		String[] toEmails = new String[emailList.size()];
		toEmails = emailList.toArray(toEmails);
		System.out.println(toEmails[0]);
		try {
			emailGen.sendEmail(subject, messageBody, toEmails, null, null);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Removes the designated Employee object from the Database
	 * 
	 * @param e The Employee object to be removed
	 */
	public void removeEmployee(Employee e) {
		employeeDAO.deleteEmployee(e);
	}

	/**
	 * Retrieves the Employee based on the provided email address
	 * 
	 * @param email The email address of the employee
	 * @return The Employee object of the email address
	 */
	public Employee retrieveEmployeeViaEmail(String email) {
		return employeeDAO.getEmployeeByEmail(email);
	}

	/**
	 * Adds a new Employee object to the Database
	 * 
	 * @param e The Employee object to be added in
	 */
	public void saveEmployee(Employee e) {
		employeeDAO.saveEmployee(e);
	}
	
	/**
	 * this suspends all employees from a company with outstanding payment and sends them a notification email
	 * @param c the Company to check and suspend employees from
	 */
	public void suspendOverduePaymentFromCompany(Company c) {
		ArrayList<String> emailList = new ArrayList<String>();

		SendEmail emailGen = new SendEmail();

		String url = "http://lunchtime.dal.jelastic.vps-host.net/eureka_webservice/pages/login.jsp";
		String subject = "Koh Bus LunchTime Ordering App - Payment Overdue";
		String messageBody = "Dear User,<br><br>"
				+ "Please note that you will not be able to place any new orders until you have cleared your payment!<br><br>"
				+ "<a href=" + url + ">" + url + "</a>" + "<br><br>" + "Regards,<br>"
				+ "Admin<br><br>"
				+ "This is a system-generated email; please DO NOT REPLY to this email.<br>";

		ArrayList<Object> objects = new ArrayList<Object>(
				MyConnection.getUsersWithOutstandingPaymentFromCompany(c));
		for (Object o : objects) {
			Employee tempEmployee = (Employee) o;
			tempEmployee.setStatus(StringValues.EMPLOYEE_SUSPENDED);
			String tempEmail = tempEmployee.getEmail();
			emailList.add(tempEmail);
			updateEmployee(tempEmployee);

		}
		String[] toEmails = new String[emailList.size()];
		toEmails = emailList.toArray(toEmails);
		System.out.println(toEmails[0]);

		try {
			emailGen.sendEmail(subject, messageBody, toEmails, null, null);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * suspend a user based on the user's email
	 * @param email the email address of the user to suspend
	 */
	public void suspendUser(String email) {
		Employee employeeToSuspend = getEmployeeByEmail(email);
		employeeToSuspend.setStatus(StringValues.EMPLOYEE_SUSPENDED);
		updateEmployee(employeeToSuspend);
	}
	/**
	 * Used to update a user's amount owed 
	 * @param arrayList Employees to update the amount owed
	 * @param amount the amount to update to
	 */
	public void updateAmountOwed(ArrayList<Employee> arrayList, double amount) {
		for (Employee employee : arrayList) {
			employee.setAmountOwed(amount);
			employeeDAO.updateEmployee(employee);
		}

	}

	/**
	 * Updates the Employee's specified food delivery point
	 * 
	 * @param email The email address of the Employee
	 * @param buildingName The name of the building for the new delivery point
	 */
	public void updateDefaultDeliveryPoint(String email, String buildingName) {
		Employee employee = retrieveEmployeeViaEmail(email);
		employee.setDeliveryPoint(buildingName);
		employeeDAO.updateEmployee(employee);
	}

	/**
	 * Updates the designated Employee object in the Database
	 * 
	 * @param e The Employee object to be updated
	 */
	public void updateEmployee(Employee e) {
		employeeDAO.updateEmployee(e);
	}

}
