package dao;

import connection.MyConnection;
import entity.FoodOrderItem;

public class FoodOrderItemDAO {
	
	public FoodOrderItemDAO() {
		
	}
	
	public static FoodOrderItem getFoodOrderItem(int foodOrderItemId) {
		return (FoodOrderItem) MyConnection.get(FoodOrderItem.class, foodOrderItemId);
	}
	
	public static void saveFoodOrderItem(FoodOrderItem f) {
		MyConnection.save(f);
	}
	
	public static void updateFoodOrderItem(FoodOrderItem f) {
		MyConnection.update(f);
	}
	
	public static void deleteFoodOrderItem(FoodOrderItem f) {
		MyConnection.delete(f);
	}
}
