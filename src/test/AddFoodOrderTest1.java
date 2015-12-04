
package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import connection.MyConnection;
import controller.FoodController;
import dao.EmployeeDAO;
import model.Employee;
import model.Food;
import model.FoodOrder;
import model.FoodOrderItem;
import model.Modifier;
import model.ModifierChosen;
import model.Stall;

public class AddFoodOrderTest1 {
	public static void main(String[] args) {
		System.out.println("Starting Test");
		FoodController foodController = new FoodController();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		// fan choy
		Food newFood = foodController.getFood(36);
		// dumpling
		Food newFood2 = foodController.getFood(37);
		Stall newStall = newFood.getStall();
		Stall newStall2 = newFood2.getStall();
		Employee tempEmployee = employeeDAO.getEmployeeByEmail("metan");
		FoodOrderItem newFoodOrderItem = new FoodOrderItem(null, newFood, 1, null);
		Modifier newModifier = new Modifier("LOL", "", 5, newFood);
		ModifierChosen modifierChosen1 = new ModifierChosen(newModifier, newFoodOrderItem);
		HashSet<ModifierChosen> modifierList = new HashSet<ModifierChosen>();
		modifierList.add(modifierChosen1);
		newFoodOrderItem.setModifierChosenList(modifierList);;
		FoodOrderItem newFoodOrderItem2 = new FoodOrderItem(null, newFood, 1, null);
		ArrayList<FoodOrderItem> foodOrderItemSet = new ArrayList<FoodOrderItem>();
		foodOrderItemSet.add(newFoodOrderItem);
		foodOrderItemSet.add(newFoodOrderItem2);
		boolean same = newFoodOrderItem.equals(newFoodOrderItem2);
		System.out.println(same);
		
		for (FoodOrderItem foodItem : foodOrderItemSet) {
			Iterator iter = foodOrderItemSet.iterator();
			while (iter.hasNext()) {

			}
		}
	}
}