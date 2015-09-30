package dao;

import model.Admin;
import connection.MyConnection;

public class AdminDAO {
	
	public AdminDAO() {
		
	}
	
	public static Admin getadmin(int adminId) {
		return (Admin) MyConnection.get(Admin.class, adminId);
	}
	
	public static void saveadmin(Admin d) {
		MyConnection.save(d);
	}
	
	public static void updateadmin(Admin d) {
		MyConnection.update(d);
	}
	
	public static void deleteadmin(Admin d) {
		MyConnection.delete(d);
	}
}
