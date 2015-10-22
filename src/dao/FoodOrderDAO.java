package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Employee;
import model.FoodOrder;
import connection.MyConnection;

public class FoodOrderDAO {

	public FoodOrderDAO() {

	}

	// Retrieve FoodOrder from the DB with foodOrderID
	public static FoodOrder getFoodOrder(int foodOrderId) {
		return (FoodOrder) MyConnection.get(FoodOrder.class, foodOrderId);
	}
	
	
	public static List<FoodOrder> getFoodOrderSet(Employee employee){
		List<FoodOrder> returnList = new ArrayList<>();
		
		List<Object> hiberList = MyConnection.getFoodOrderList(employee);
		if (hiberList.size() != 0) {
			for (Object o : hiberList) {
				returnList.add((FoodOrder) o);
			}
		} 
		return returnList;
	}

	// Retrive FoodOrders from the DB between two datetimes with format
	// "yyyy-MM-dd hh:mm:ss"
	
	
	public static List<FoodOrder> getFoodOrderByDate(Date past, Date present) {
		List<FoodOrder> returnList = new ArrayList<>();
		List<Object> lister = MyConnection.getFoodOrderBetween(past, present);
		if (lister.size() != 0) {
			for (Object o : lister) {
				returnList.add((FoodOrder) o);
			}
		} 
		return returnList;
	}
	
	public static List<FoodOrder> getFoodOrderByDateAndTime(String past, String present) {
		String sqlQuery = "SELECT * FROM  foodorder where createDate>= + :date1 and createDate<=:date2";
		
		List<FoodOrder> returnList = new ArrayList<>();
		List<Object> lister = MyConnection.get(sqlQuery, past, present);
		if (lister.size() != 0) {
			for (Object o : lister) {
				returnList.add((FoodOrder) o);
			}
		} 
		return returnList;
	}

	// Retrieve FoodOrders from the DB by date with fomat "yyyy-MM-dd"
	public static List<FoodOrder> getFoodOrderByDate(String date) {
		String sqlQuery = "SELECT * FROM  foodorder where createDate>=\"" + date + "\"";
		List<FoodOrder> returnList = new ArrayList<>();
		List<Object> lister = MyConnection.get(sqlQuery);
		if (lister.size() != 0) {
			for (Object o : lister) {
				returnList.add((FoodOrder) o);
			}
		}
		return returnList;
	}

	// Save new FoodOrder to DB
	public static void saveFoodOrder(FoodOrder f) {
		MyConnection.save(f);
	}

	// Update existing FoodOrder in DB
	public static void updateFoodOrder(FoodOrder f) {
		MyConnection.update(f);
	}

	// Delete FoodOrder from DB
	public static void deleteFoodOrder(FoodOrder f) {
		MyConnection.delete(f);
	}
}
