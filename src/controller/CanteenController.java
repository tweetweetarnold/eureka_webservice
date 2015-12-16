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
	 * @return A list of all the Canteen objects from the database
	 */
	public List<Canteen> retrieveAllCanteens() {
		List<Canteen> list = canteenDAO.getAllCanteens();
		System.out.println(list);
		return list;
	}
	
	/**
	 * Retrieves All the Food stored in the database
	 * @return A list of all the Food objects from the database
	 */
	public List<Food> getAllFood() {
		List<Food> returnList = new ArrayList<Food>();
		List<Canteen> canteenList = retrieveAllCanteens();

		for (Canteen c : canteenList) {
			Set<Stall> stallList = c.getStallList();
			for (Stall s : stallList) {
				Set<Food> foodList = s.getFoodList();
				returnList.addAll(foodList);
			}
		}
		return returnList;
	}

}
