package dao;

import connection.MyConnection;
import entity.Employee;

public class EmployeeDAO {

	public EmployeeDAO() {
	}

	public static Employee getUser(int id) {
		return (Employee) MyConnection.get(Employee.class, id);
	}

}
