package dao;

import connection.MyConnection;
import entity.Employee;

public class EmployeeDAO {

	public EmployeeDAO() {
	}

	public static Employee getEmployee(int id) {
		return (Employee) MyConnection.get(Employee.class, id);
	}
	
	//BH added a new getEmployeeUsername method that parses in String username
	public static Employee getEmployeeUsername(String username) {
		return (Employee) MyConnection.getUser(Employee.class, username);
	}

	public static void saveEmployee(Employee e) {
		MyConnection.save(e);
	}
	
	public static void updateEmployee(Employee e) {
		MyConnection.update(e);
	}
	
	public static void deleteEmployee(Employee e) {
		MyConnection.delete(e);
	}
	
	
	public Employee getEmployeeTEMPORARY(String username){
	
		//temporary EmployeeObject REMOVE WHEN DATABASE IS UP!
		Employee tempEmployee = new Employee("chris.cheng.2013", "1234", "Chris", "1441", 92742509, null, null, null);
		if(username.equals(tempEmployee.getUsername())){
			return tempEmployee;
		}
		return null;
	
	}
}
