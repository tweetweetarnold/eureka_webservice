package dao;

import model.Admin;
import connection.MyConnection;

public class AdminDAO {
	
	public AdminDAO() {
		
	}
	//retrieve Admin from the DB with adminId
	public static Admin getadmin(int adminId) {
		return (Admin) MyConnection.get(Admin.class, adminId);
	}
	//Save Admin to the DB
	public static void saveadmin(Admin d) {
		MyConnection.save(d);
	}
	//Update Admin in the DB
	public static void updateadmin(Admin d) {
		MyConnection.update(d);
	}
	//Delete Admin from the DB
	public static void deleteadmin(Admin d) {
		MyConnection.delete(d);
	}
}
