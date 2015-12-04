package controller;

import model.Food;
import dao.FoodDAO;

public class FoodController {
	FoodDAO foodDAO = new FoodDAO();

	public String getFoodImageDirectory(int id) {
		return foodDAO.getFood(id).getImageDirectory();
	}

	public Food getFood(int id) {
		return foodDAO.getFood(id);
	}

}
