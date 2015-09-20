package controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.FoodOrderDAO;
import entity.FoodOrder;

public class FoodOrderController {
	FoodOrderDAO foodOrderDAO = new FoodOrderDAO();
	
	public FoodOrderController(){
		
	}
	
	public void addFoodOrder(FoodOrder f){
		foodOrderDAO.saveFoodOrder(f);
	}
	
	public FoodOrder getFoodOrder(int foodOrderId){
		return foodOrderDAO.getFoodOrder(foodOrderId);
	}
	
	public List getFoodOrderToday(){
		Date today = new Date();
		String dateChooser = today.toString();
		String fin = "";
		int index = dateChooser.indexOf(" ");
		fin += dateChooser.substring(0, index);
		fin +=" 00:00:00";
		return foodOrderDAO.getFoodOrderByDate(fin);
	}
	
}
