package dao;

import connection.MyConnection;
import entity.Stall;

public class StallDAO {
	
	public StallDAO() {
		
	}
	
	public static Stall getStall(int stallId) {
		return (Stall) MyConnection.get(Stall.class, stallId);
	}
	
	public static void saveStall(Stall h) {
		MyConnection.save(h);
	}
	
	public static void updateStall(Stall h) {
		MyConnection.update(h);
	}
	
	public static void deleteStall(Stall h) {
		MyConnection.delete(h);
	}
}
