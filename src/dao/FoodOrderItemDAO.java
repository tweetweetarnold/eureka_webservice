package dao;

import model.Food;
import model.FoodOrderItem;

import java.util.Date;
import java.util.List;

import connection.MyConnection;

/**
 * Performs the function of Data Access Object for FoodOrderItem model
 * 
 * @author SMU Team Eureka
 */
public class FoodOrderItemDAO {

	/**
	 * Creates a default constructor for FoodOrderItemDAO
	 */
	public FoodOrderItemDAO() {
		
	}
	
	/**
	 * Removes the designated FoodOrderItem object from the database
	 * 
	 * @param f The FoodOrderItem object to be removed
	 */
	public void deleteFoodOrderItem(FoodOrderItem f) {
		MyConnection.delete(f);
	}

	/**
	 * Retrieves the FoodOrderItem based on the provided ID
	 * 
	 * @param foodOrderItemId  The ID used for retrieving the FoodOrderItem
	 * @return The FoodOrderItem object that has the provided ID
	 */
	public FoodOrderItem getFoodOrderItem(int foodOrderItemId) {
		return (FoodOrderItem) MyConnection.get(FoodOrderItem.class, foodOrderItemId);
	}

	/**
	 * Retrieve the FoodOrderItems that falls within range of the provided dates
	 * 
	 * @param food The Food object
	 * @param earlierDate The starting Date for the range
	 * @param laterDate The ending Date for the range
	 * @return The List of FoodOrderItems that falls within the range of the provided dates
	 */
	public List<Object> getFoodOrderItems(Food food, Date earlierDate, Date laterDate){
		return MyConnection.getFoodOrderItemList(food, earlierDate, laterDate);
	}

	/**
	 * Adds a new FoodOrderItem to the database
	 * 
	 * @param f The FoodOrderItem object to be added in
	 */
	public void saveFoodOrderItem(FoodOrderItem f) {
		MyConnection.save(f);
	}
	
	/**
	 * Updates the designated FoodOrderItem object in the database
	 * 
	 * @param f The FoodOrderItem object to be updated
	 */
	public void updateFoodOrderItem(FoodOrderItem f) {
		MyConnection.update(f);
	}
	
}
