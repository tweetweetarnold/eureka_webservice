package dao;

import java.util.ArrayList;
import java.util.List;

import model.Canteen;
import model.FoodOrder;
import connection.MyConnection;

public class FoodOrderDAO {
	
	public FoodOrderDAO() {
		
	}
	
	public static FoodOrder getFoodOrder(int foodOrderId) {
		return (FoodOrder) MyConnection.get(FoodOrder.class, foodOrderId);
	}
	
	public static List getFoodOrderByDate(String date) {
		String sqlQuery = "SELECT * FROM  foodorder where createDate>=\"" + date+ "\"";
		System.out.println(sqlQuery);
		List<FoodOrder> returnList = new ArrayList<>();
		List<Object> lister = MyConnection.get(sqlQuery);
		System.out.println(lister.size());
		
		for(Object o : lister) {
			returnList.add((FoodOrder) o);
		}
		return returnList;
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
