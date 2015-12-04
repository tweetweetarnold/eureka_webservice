package controller;

import model.Employee;
import dao.EmployeeDAO;

public class UserController {
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

	public Employee retrieveEmployeeViaEmail(String email) {
		return employeeDAO.getEmployeeByEmail(email);
	}
}
