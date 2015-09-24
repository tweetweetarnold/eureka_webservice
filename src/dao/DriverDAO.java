package dao;

import model.Driver;
import connection.MyConnection;

public class DriverDAO {
	
	public DriverDAO() {
		
	}
	
	public static Driver getDriver(int driverId) {
		return (Driver) MyConnection.get(Driver.class, driverId);
	}
	
	public static void saveDriver(Driver d) {
		MyConnection.save(d);
	}
	
	public static void updateDriver(Driver d) {
		MyConnection.update(d);
	}
	
	public static void deleteDriver(Driver d) {
		MyConnection.delete(d);
	}
}
