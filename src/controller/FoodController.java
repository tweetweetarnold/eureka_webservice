package controller;

import dao.FoodDAO;
import model.Food;

public class FoodController {
	
	
	public byte[] getFoodImage(int id) {
		Food food = FoodDAO.getFood(id);
		
		return food.getImage();
	}

}
