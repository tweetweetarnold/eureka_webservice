package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.Canteen;
import model.Food;
import model.Stall;
import dao.CanteenDAO;

public class CanteenController {

	public List<Canteen> retrieveAllCanteens() {
		List<Canteen> list = CanteenDAO.getAllCanteens();
		System.out.println(list);
		return list;
	}

	public List<Food> getAllFood() {
		List<Food> returnList = new ArrayList<Food>();
		List<Canteen> canteenList = retrieveAllCanteens();

		for (Canteen c : canteenList) {
			Set<Stall> stallList = c.getStallList();
			for (Stall s : stallList) {
				Set<Food> foodList = s.getFoodList();
				returnList.addAll(foodList);
			}
		}
		return returnList;
	}

}
