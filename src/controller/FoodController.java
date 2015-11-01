package controller;

import model.Food;
import dao.FoodDAO;

public class FoodController {

	public byte[] getFoodImage(int id) {
		return FoodDAO.getFood(id).getImage();
	}

	public Food getFood(int id) {
		return FoodDAO.getFood(id);
	}

}
