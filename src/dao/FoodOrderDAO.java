package dao;

import java.util.ArrayList;
import java.util.List;

import model.FoodOrder;
import connection.MyConnection;

public class FoodOrderDAO {

	public FoodOrderDAO() {

	}
	//Retrieve FoodOrder from the DB with foodOrderID
	public static FoodOrder getFoodOrder(int foodOrderId) {
		return (FoodOrder) MyConnection.get(FoodOrder.class, foodOrderId);
	}
	//Retrive FoodOrders from the DB between two datetimes with format "yyyy-MM-dd hh:mm:ss"
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
	//Retrieve FoodOrders from the DB by date with fomat "yyyy-MM-dd"
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
	//Save new FoodOrder to DB
	public static void saveFoodOrder(FoodOrder f) {
		MyConnection.save(f);
	}
	//Update existing FoodOrder in DB
	public static void updateFoodOrder(FoodOrder f) {
		MyConnection.update(f);
	}
	//Delete FoodOrder from DB
	public static void deleteFoodOrder(FoodOrder f) {
		MyConnection.delete(f);
	}
}
