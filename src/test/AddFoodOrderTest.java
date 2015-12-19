
package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import model.Employee;
import model.Food;
import model.FoodOrder;
import model.FoodOrderItem;
import model.Stall;
import connection.MyConnection;
import controller.FoodController;
import controller.FoodOrderController;
import dao.EmployeeDAO;

public class AddFoodOrderTest {
	public static void main(String[] args) {
		System.out.println("Starting Test");
		FoodController foodController = new FoodController();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		// fan choy
		Food newFood = foodController.getFood(36);
		Food favFood = foodController.getFood(2);
		Stall newStall = newFood.getStall();
		Employee tempEmployee = employeeDAO.getEmployeeByEmail("arnold.lee.2013@sis.smu.edu.sg");
		tempEmployee.setFavoriteFood(favFood);
		employeeDAO.updateEmployee(tempEmployee);
		
		
		FoodOrderItem newFoodOrderItem = new FoodOrderItem(null, newFood, 1, null);
		HashSet<FoodOrderItem> foodOrderItemSet = new HashSet<FoodOrderItem>();
		foodOrderItemSet.add(newFoodOrderItem);
		FoodOrder newFoodOrder = new FoodOrder("TEST", tempEmployee, foodOrderItemSet, null);
		newFoodOrderItem.setFoodOrder(newFoodOrder);
		// date processing
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date laterDate = cal.getTime();
		laterDate.setHours(10);
		laterDate.setMinutes(0);
		cal.add(Calendar.DATE, -1);
		Date earlierDate = cal.getTime();
		earlierDate.setHours(10);
		earlierDate.setMinutes(0);

		MyConnection.save(newFoodOrder);
		FoodOrderController foodOrderController = new FoodOrderController();
		ArrayList<FoodOrder> foodOrderList = new ArrayList<FoodOrder>(foodOrderController.getFoodOrderBetweenCutOff(earlierDate, laterDate));
		System.out.println("Here is the start of the test " + foodOrderList.size());
		
		System.out.println("Start: "+ earlierDate +"    End: " + laterDate );
		foodOrderController.replaceWithFavoriteFood(36, earlierDate, laterDate);
		
		System.out.println("Food : " + newFood.getName() + " was replaced by Fav Food: " + favFood.getName());
		
		
	}
}