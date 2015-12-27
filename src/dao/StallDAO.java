package dao;

import model.Canteen;
import model.Food;
import model.Stall;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import connection.MyConnection;

public class StallDAO {

	CanteenDAO canteenDAO = new CanteenDAO();
	FoodDAO foodDAO = new FoodDAO();
	
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
	
	public void addFoodToStall(Stall s, Food f) {
		Set<Food> foodList = s.getFoodList();
		if (foodList == null) {
			foodList = new HashSet<Food>();
		}
		foodList.add(f);
		updateStall(s);
	}
	
	public void loadStallData(List<String[]> content) {
		Canteen canteen = null;
		Iterator iter = content.iterator();
        iter.next();
        while (iter.hasNext()) {
            String[] row = (String[]) iter.next();
            String stallName = row[0].trim();
            String contactNum = row[1].trim();
            long contactNumber = Long.parseLong(contactNum);
            String canteenName = row[2].trim();
            canteen = canteenDAO.getCanteenByName(canteenName);
           
            Stall newStall = new Stall(stallName, contactNumber, canteen, null, null);
         //   stallList.add(newStall);
            canteenDAO.addStallToCanteen(canteen, newStall);
            saveStall(newStall);
        }
	}
	
}
