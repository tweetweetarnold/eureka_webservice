package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.EmployeeDAO;
import dao.FoodDAO;
import dao.FoodOrderDAO;
import dao.StallDAO;
import model.Canteen;
import model.Employee;
import model.Food;
import model.FoodOrder;
import model.Modifier;
import model.ModifierSection;
import model.Stall;
import controller.CanteenController;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Testtest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// EmployeeDAO employeeDAO = new EmployeeDAO();
		// Employee employee =
		// employeeDAO.getEmployeeByEmail("chris.cheng.2013@sis.smu.edu.sg");
		// FoodOrderDAO foodOrderDAO = new FoodOrderDAO();
		// List<FoodOrder> foodOrderList =
		// foodOrderDAO.getFoodOrderSet(employee);
		// for(FoodOrder foodOrder : foodOrderList){
		// System.out.println(foodOrder.getCreateDate());
		// }
		
//		FoodDAO foodDAO = new FoodDAO();
//		Food food = foodDAO.getFood(157);
//		Set<ModifierSection> modifierSection = food.getModifierSectionList();
//		System.out.println(modifierSection.size());
		
		CanteenController canteenController = new CanteenController();
		Canteen c = canteenController.getCanteen(1);
		FoodDAO foodDAO = new FoodDAO();
		List<Food> list = foodDAO.searchFoodFromCanteen(c, "chi");
		System.out.println(list.size());
		try{
			System.out.println("Canteen number = " + list.get(0).getStall().getCanteen().getCanteenId());
			for(Food f : list){
				System.out.println(f.getName());
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
