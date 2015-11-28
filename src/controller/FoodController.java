package controller;

import model.Food;
import dao.FoodDAO;

public class FoodController {

	public String getFoodImageDirectory(int id) {
		return FoodDAO.getFood(id).getImageDirectory();
	}

	public Food getFood(int id) {
		return FoodDAO.getFood(id);
	}

}
