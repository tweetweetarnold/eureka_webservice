package dao;

import model.Food;
import connection.MyConnection;

public class FoodDAO {
	
	public FoodDAO() {
		
	}
	//Retrieve Food from the DB with foodID
	public static Food getFood(int foodId) {
		return (Food) MyConnection.get(Food.class, foodId);
	}
	//Save new Food in the DB
	public static void saveFood(Food f) {
		MyConnection.save(f);
	}
	//Update existing Food in the DB
	public static void updateFood(Food f) {
		MyConnection.update(f);
	}
	//Delete Food from the DB
	public static void deleteFood(Food f) {
		MyConnection.delete(f);
	}
}
