package controller;

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
	
}
