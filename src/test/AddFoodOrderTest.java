
package test;

import java.util.HashSet;

import model.Employee;
import model.Food;
import model.FoodOrder;
import model.FoodOrderItem;
import model.Stall;
import connection.MyConnection;
import controller.FoodController;
import dao.EmployeeDAO;

public class AddFoodOrderTest{
	public static void main(String[] args){
		System.out.println("Starting Test");
		FoodController foodController = new FoodController();
		//fan choy
		Food newFood = foodController.getFood(36);
		Stall newStall = newFood.getStall();
		Employee tempEmployee = EmployeeDAO.getEmployeeByEmail("metan");
		FoodOrderItem newFoodOrderItem = new FoodOrderItem(null, newFood, 1, null);
		HashSet<FoodOrderItem> foodOrderItemSet = new HashSet<FoodOrderItem>();
		foodOrderItemSet.add(newFoodOrderItem);
		FoodOrder newFoodOrder = new FoodOrder("TEST", tempEmployee, foodOrderItemSet);

		MyConnection.save(newFoodOrder);
		
	}
}