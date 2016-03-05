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

	public void suspendOverduePaymentFromCompany(Company c) {

		ArrayList<String> emailList = new ArrayList<String>();
		SendEmail emailGen = new SendEmail();
		emailGen.setMailServerProperties();
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
		String[] ccEmails = { "sumon123may@eastman.com", "wch123ow@eastman.com" };
		try {
			emailGen.sendEmailWithCarbonCopy(subject, messageBody, toEmails, ccEmails);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void suspendUser(String email) {
		Employee employeeToSuspend = getEmployeeByEmail(email);
		employeeToSuspend.setStatus(StringValues.EMPLOYEE_SUSPENDED);
		updateEmployee(employeeToSuspend);
	}

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
	
	public void notifyAllEmployeesFromCompanyWithDeliveryPoint(Company c, String deliveryPoint){
		ArrayList<Employee> results = new ArrayList<Employee>();
		results = employeeDAO.getAllEmployeesFromCompanyWithDeliveryPoint(c, deliveryPoint);
		
		
		ArrayList<String> emailList = new ArrayList<String>();
		SendEmail emailGen = new SendEmail();
		emailGen.setMailServerProperties();
		String url = "http://lunchtime.dal.jelastic.vps-host.net/eureka_webservice/pages/login.jsp";
		String subject = "Koh Bus LunchTime Ordering App - Please Update Delivery Building";
		String messageBody = "Dear User,<br><br>"
				+ "Please note that your chosen delivery building is no longer valid please specify a new building.<br><br>"
				+ "You can select a new building from the edit profile page after you login. <br><br>"
				+ "<a href=" + url + ">" + url + "</a>" + "<br><br>" + "Regards,<br>"
				+ "Admin<br><br>"
				+ "This is a system-generated email; please DO NOT REPLY to this email.<br>";
		
		for(Employee e :results){
			String tempEmail = e.getEmail();
			emailList.add(tempEmail);
		}
		String[] toEmails = new String[emailList.size()];
		toEmails = emailList.toArray(toEmails);
		System.out.println(toEmails[0]);
		String[] ccEmails = { "sumon123may@eastman.com", "wch123ow@eastman.com" };
		try {
			emailGen.sendEmailWithCarbonCopy(subject, messageBody, toEmails, ccEmails);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
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
