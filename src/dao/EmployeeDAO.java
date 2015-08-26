package dao;

import connection.MyConnection;
import entity.Employee;

public class EmployeeDAO {

	public EmployeeDAO() {
	}

	public static Employee getEmployee(int id) {
		return (Employee) MyConnection.get(Employee.class, id);
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
}
