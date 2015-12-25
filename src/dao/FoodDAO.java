package dao;

import model.Food;
import model.Modifier;
import model.Stall;

import java.util.Set;

import connection.MyConnection;

public class FoodDAO {
	CanteenDAO canteenDAO = new CanteenDAO();
	
	// Retrieve Food from the DB with foodID
	public Food getFood(int foodId) {
		return (Food) MyConnection.get(Food.class, foodId);
	}

	// Save new Food in the DB
	public void saveFood(Food f) {
		MyConnection.save(f);
	}

	// Update existing Food in the DB
	public void updateFood(Food f) {
		MyConnection.update(f);
	}

	// Delete Food from the DB
	public void deleteFood(Food f) {
		MyConnection.delete(f);
	}
	
	public Food getFoodFromFoodList(Set<Food>foodList, String foodName) {
		for (Food f : foodList) {
			if (f.getName().equals(foodName)) {
				return f;
			}
		}
		return null;
	}
	
	public Food getFoodFromStallAndCanteen(String foodName, String stallName, String canteenName) {
		Stall s = canteenDAO.getStallFromCanteen(canteenName, stallName);
		Set<Food> foodList = s.getFoodList();
		return getFoodFromFoodList(foodList, foodName);
		
	}
	
	public void addModifierToFood(Modifier m, Food f) {
		Set<Modifier> modifierList = f.getModifierList();
		
		modifierList.add(m);
		updateFood(f);
		
	}
}
