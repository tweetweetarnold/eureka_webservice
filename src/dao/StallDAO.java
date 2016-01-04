package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import model.Canteen;
import model.Food;
import model.FoodOrder;
import model.Stall;
import connection.MyConnection;

/**
 * Performs the function of Data Access Object for the Stall model
 * 
 * 
 */
public class StallDAO {

	/**
	 * Creates a default constructor for StallDAO
	 */
	public StallDAO() {

	}

	/**
	 * Retrieves the Stall based on the provided ID
	 * 
	 * @param stallId The ID used for retrieving the Stall
	 * @return The Stall object that has the provided ID
	 */
	public Stall getStall(int stallId) {
		return (Stall) MyConnection.get(Stall.class, stallId);
	}

	/**
	 * Adds a new Stall object to the database
	 * 
	 * @param h The Stall object to be added in
	 */
	public void saveStall(Stall h) {
		MyConnection.save(h);
	}

	/**
	 * Updates the designated Stall object in the database
	 * 
	 * @param h The Stall object to be updated
	 */
	public void updateStall(Stall h) {
		MyConnection.update(h);
	}

	/**
	 * Removes the designated Stall object from the database
	 * 
	 * @param h The Stall object to be removed
	 */
	public void deleteStall(Stall h) {
		MyConnection.delete(h);
	}

	/**
	 * Add a new Food to the designated Stall
	 * 
	 * @param s The designated Stall to add the Food
	 * @param f The Food to be added
	 */
	public void addFoodToStall(Stall s, Food f) {
		Set<Food> foodList = s.getFoodList();
		if (foodList == null) {
			foodList = new HashSet<Food>();
		}
		foodList.add(f);
		updateStall(s);
	}

	/**
	 * Load the validated content of the Stall.csv into the database
	 * 
	 * @param content The list of Stall data to be loaded into the database
	 */
	public void loadStallData(List<String[]> content) {

		CanteenDAO canteenDAO = new CanteenDAO();
		Canteen canteen = null;
		Iterator<String[]> iter = content.iterator();
		iter.next();
		while (iter.hasNext()) {
			String[] row = (String[]) iter.next();
			String stallName = row[0].trim();
			String contactNum = row[1].trim();
			long contactNumber = Long.parseLong(contactNum);
			String canteenName = row[2].trim();
			canteen = canteenDAO.getCanteenByName(canteenName);

			Stall newStall = new Stall(stallName, contactNumber, canteen, null, null);
			// stallList.add(newStall);
			canteenDAO.addStallToCanteen(canteen, newStall);
			saveStall(newStall);
		}
	}

	public ArrayList<Stall> getAllStallsUnderCanteen(Canteen canteen) {
		ArrayList<Stall> returnList = new ArrayList<Stall>();

		DetachedCriteria dc = DetachedCriteria.forClass(Stall.class);
		dc.add(Restrictions.eq("canteen", canteen));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((Stall) o);
		}
		return returnList;
	}

}
