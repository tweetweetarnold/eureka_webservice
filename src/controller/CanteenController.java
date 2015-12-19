package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.Canteen;
import model.Food;
import model.Stall;
import dao.CanteenDAO;

/**
 * Process the business logic of managing the Canteen for the web application
 * 
 * 
 */
public class CanteenController {
	CanteenDAO canteenDAO = new CanteenDAO();

	/**
	 * Creates a default constructor for CanteenController
	 */
	public CanteenController() {

	}

	/**
	 * Retrieves All the Canteens stored in the database
	 * 
	 * @return A list of all the Canteen objects from the database
	 */
	public ArrayList<Canteen> getAllCanteens() {
		return canteenDAO.getAllCanteens();
	}

	/**
	 * Retrieves All the Food stored in the database
	 * 
	 * @return A list of all the Food objects from the database
	 */
	public List<Food> getAllFood() {
		List<Food> returnList = new ArrayList<Food>();
		List<Canteen> canteenList = getAllCanteens();

		for (Canteen c : canteenList) {
			Set<Stall> stallList = c.getStallList();
			for (Stall s : stallList) {
				Set<Food> foodList = s.getFoodList();
				returnList.addAll(foodList);
			}
		}
		return returnList;
	}

	/**
	 * Retrieve a Canteen from the database by an ID
	 * 
	 * @param canteenId The ID that belongs to the Canteen
	 * @return A Canteen object that has the specified ID
	 */
	public Canteen getCanteen(int canteenId) {
		return canteenDAO.getCanteen(canteenId);
	}

}
