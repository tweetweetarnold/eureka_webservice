package dao;

import connection.MyConnection;
import entity.FoodOrder;

public class FoodOrderDAO {
	
	public FoodOrderDAO() {
		
	}
	
	public static FoodOrder getFoodOrder(int foodOrderId) {
		return (FoodOrder) MyConnection.get(FoodOrder.class, foodOrderId);
	}
	
	public static FoodOrder getFoodOrderByDate(Date ) {
		return (FoodOrder) MyConnection.get(FoodOrder.class, foodOrderId);
	}
	
	
	public static void saveFoodOrder(FoodOrder f) {
		MyConnection.save(f);
	}
	
	public static void updateFoodOrder(FoodOrder f) {
		MyConnection.update(f);
	}
	
	public static void deleteFoodOrder(FoodOrder f) {
		MyConnection.delete(f);
	}
}
