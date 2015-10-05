package dao;

import model.FoodOrderItem;
import connection.MyConnection;

public class FoodOrderItemDAO {
	
	public FoodOrderItemDAO() {
		
	}
	//Retrieve FoodOrderItem from DB with FoodOrderItemID
	public static FoodOrderItem getFoodOrderItem(int foodOrderItemId) {
		return (FoodOrderItem) MyConnection.get(FoodOrderItem.class, foodOrderItemId);
	}
	//Save new FoodOrderItem to DB
	public static void saveFoodOrderItem(FoodOrderItem f) {
		MyConnection.save(f);
	}
	//Update existing FoodOrderItem to DB 
	public static void updateFoodOrderItem(FoodOrderItem f) {
		MyConnection.update(f);
	}
	//Delete FoodOrderItem from DB
	public static void deleteFoodOrderItem(FoodOrderItem f) {
		MyConnection.delete(f);
	}
}
