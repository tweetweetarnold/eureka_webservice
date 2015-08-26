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

}
