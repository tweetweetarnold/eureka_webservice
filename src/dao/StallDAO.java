package dao;

import model.Stall;
import connection.MyConnection;

public class StallDAO {
	
	public StallDAO() {
		
	}
	//Retrieve Stall from DB with stallID
	public static Stall getStall(int stallId) {
		return (Stall) MyConnection.get(Stall.class, stallId);
	}
	//Save new Stall in DB
	public static void saveStall(Stall h) {
		MyConnection.save(h);
	}
	//Update existing Stall in DB
	public static void updateStall(Stall h) {
		MyConnection.update(h);
	}
	//Delete Stall from DB
	public static void deleteStall(Stall h) {
		MyConnection.delete(h);
	}
}
