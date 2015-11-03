package controller;

import model.Food;
import dao.FoodDAO;

public class FoodController {

	public String getImageDirectory(int id) {
		return FoodDAO.getFood(id).getImageDir();
	}

	public Food getFood(int id) {
		return FoodDAO.getFood(id);
	}

}
