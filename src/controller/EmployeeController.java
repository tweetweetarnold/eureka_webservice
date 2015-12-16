package controller;

import model.Employee;
import dao.EmployeeDAO;

public class EmployeeController {
	EmployeeDAO employeeDAO = new EmployeeDAO();

	public void updateEmployee(Employee e) {
		employeeDAO.updateEmployee(e);
	}

	public void removeEmployee(Employee e) {
		employeeDAO.deleteEmployee(e);
	}

	public void updateDefaultDeliveryPoint(String email, String buildingName) {
		Employee employee = retrieveEmployeeViaEmail(email);
		employee.setDefaultDeliveryPoint(buildingName);
		employeeDAO.updateEmployee(employee);
	}
	
	public String getDefaultDeliveryPoint(String email){
		Employee employee = retrieveEmployeeViaEmail(email);
		return employee.getDefaultDeliveryPoint();
	}

	public Employee retrieveEmployeeViaEmail(String email) {
		return employeeDAO.getEmployeeByEmail(email);
	}
}