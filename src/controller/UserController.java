package controller;

import model.Employee;
import dao.EmployeeDAO;
public class UserController {
	EmployeeDAO employeeDAO = new EmployeeDAO();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}
	
	public boolean addNewEmployee(Employee newEmployee) {
		String username = newEmployee.getUsername();
		//checking for duplicates
		Employee empInDatabase = EmployeeDAO.getEmployeeUsername(username);
		if (empInDatabase == null) {
			EmployeeDAO.saveEmployee(newEmployee);
			return true;
		} else {
			return false;
		}
	}
	
	public void updateEmployee(Employee e) {
		EmployeeDAO.updateEmployee(e);
		//EmployeeDAO.saveEmployee(e);
		
	}
	
	public void removeEmployee(Employee e) {
		EmployeeDAO.deleteEmployee(e);
	}
	
	public Employee retrieveEmployee(int id){
		Employee tempE = employeeDAO.getEmployee(id);
		return tempE;

	}
	//Employee ID not set yet so search by username
	public Employee retrieveEmployeeTEMPORARY(String username){
		Employee tempE = employeeDAO.getEmployeeTEMPORARY(username);
		return tempE;

	}
	
	
}
