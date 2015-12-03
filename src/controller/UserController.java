package controller;

import model.Employee;
import dao.EmployeeDAO;

public class UserController {
	EmployeeDAO employeeDAO = new EmployeeDAO();

	public void updateEmployee(Employee e) {
		EmployeeDAO.updateEmployee(e);
		// EmployeeDAO.saveEmployee(e);
	}

	public void removeEmployee(Employee e) {
		EmployeeDAO.deleteEmployee(e);
	}
	public void updateDefaultBuilding(String email, String buildingName){
		Employee employee = retrieveEmployeeViaEmail(email);
		employee.setBuilding(buildingName);
		EmployeeDAO.updateEmployee(employee);
		
	}
	
	public Employee retrieveEmployeeViaEmail(String email) {
		Employee employee = EmployeeDAO.getEmployeeByEmail(email);

		return employee;

	}
}
