package dao;

import model.Stall;
import connection.MyConnection;

public class StallDAO {

	// Retrieve Stall from DB with stallID
	public Stall getStall(int stallId) {
		return (Stall) MyConnection.get(Stall.class, stallId);
	}

	// Save new Stall in DB
	public void saveStall(Stall h) {
		MyConnection.save(h);
	}

	// Update existing Stall in DB
	public void updateStall(Stall h) {
		MyConnection.update(h);
	}

	// Delete Stall from DB
	public void deleteStall(Stall h) {
		MyConnection.delete(h);
	}
}
