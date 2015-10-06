package dao;

import model.Admin;

import java.util.List;

import connection.MyConnection;

public class AdminDAO {
	
	public AdminDAO() {
		
	}
	//retrieve Admin from the DB with adminId
	public static Admin getadmin(int adminId) {
		return (Admin) MyConnection.get(Admin.class, adminId);
	}
	
	//Retrieve Admin from DB using username
	public static Admin getadminByUsername(String username){
		String sqlQuery = "SELECT * FROM admin where username=\""
				+ username + "\"";
		List<Object> lister = MyConnection.getAdmin(sqlQuery);

		for (Object o : lister) {
			Admin admin = (Admin) o;
			if (admin.getUsername().equals(username)) {
				return admin;
			}
			
		}
		return null;
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
