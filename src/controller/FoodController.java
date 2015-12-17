package controller;

import model.Food;
import dao.FoodDAO;

/**
 * Process the function of managing the Food objects' information
 * 
 * 
 */
public class FoodController {
	FoodDAO foodDAO = new FoodDAO();

	/**
	 * Creates a default constructor for FoodController
	 */
	public FoodController() {

	}

	/**
	 * Retrieving the image directory of the Food object based on the specified ID
	 * 
	 * @param id The ID used for retrieving the Food
	 * @return The image directory of the Food object
	 */
	public String getFoodImageDirectory(int id) {
		return foodDAO.getFood(id).getImageDirectory();
	}

	/**
	 * Retrieve the Food object based on the specified ID
	 * 
	 * @param id The ID used for retrieving the Food
	 * @return The Food that has the specified ID
	 */
	public Food getFood(int id) {
		return foodDAO.getFood(id);
	}

}
