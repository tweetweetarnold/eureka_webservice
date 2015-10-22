package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.Canteen;
import model.Food;
import model.Stall;
import dao.CanteenDAO;

public class CanteenController {
	
	public List<Canteen> retrieveAll(){
		System.out.println(CanteenDAO.getAllCanteens());
		return CanteenDAO.getAllCanteens();
	}
	
	public List<Food> getAllFood(){
		List<Food> returnList = new ArrayList<Food>();
		List<Canteen> canteenList = retrieveAll();
		
		for(Canteen c : canteenList) {
			Set<Stall> stallList = c.getStallList();
			for(Stall s : stallList) {
				Set<Food> foodList = s.getFoodList();
				returnList.addAll(foodList);
			}
		}
		System.out.println("returnList : " + returnList);
		return returnList;
	}
	
	
	
}
