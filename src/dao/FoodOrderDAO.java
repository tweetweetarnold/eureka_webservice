package dao;

import java.util.ArrayList;
import java.util.List;

import model.FoodOrder;
import connection.MyConnection;

public class FoodOrderDAO {

	public FoodOrderDAO() {

	}

	public static FoodOrder getFoodOrder(int foodOrderId) {
		return (FoodOrder) MyConnection.get(FoodOrder.class, foodOrderId);
	}
	
	public static List<FoodOrder> getFoodOrderByDateAndTime(String past, String present){
		String sqlQuery = "SELECT * FROM  foodorder where createDate>=\""
				+ past + "\" and createDate<=\""
				+ present + "\"";
		List<FoodOrder> returnList = new ArrayList<>();
		List<Object> lister = MyConnection.get(sqlQuery);

		for (Object o : lister) {
			returnList.add((FoodOrder) o);
		}
		return returnList;
	}

	public static List<FoodOrder> getFoodOrderByDate(String date) {
		String sqlQuery = "SELECT * FROM  foodorder where createDate>=\""
				+ date + "\"";
		List<FoodOrder> returnList = new ArrayList<>();
		List<Object> lister = MyConnection.get(sqlQuery);

		for (Object o : lister) {
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
