package dao;

import model.Food;
import connection.MyConnection;

public class FoodDAO {
	
	public FoodDAO() {
		
	}
	
	public static Food getFood(int foodId) {
		return (Food) MyConnection.get(Food.class, foodId);
	}
	
	public static void saveFood(Food f) {
		MyConnection.save(f);
	}
	
	public static void updateFood(Food f) {
		MyConnection.update(f);
	}
	
	public static void deleteFood(Food f) {
		MyConnection.delete(f);
	}
}
