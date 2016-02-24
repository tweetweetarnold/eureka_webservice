package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dao.FoodOrderDAO;
import dao.OrderWindowDAO;
import model.Canteen;
import model.Food;
import model.FoodOrder;
import model.FoodOrderItem;
import model.OrderWindow;

public class AnalyticsController {
	OrderWindowDAO orderWindowDAO = new OrderWindowDAO();
	FoodOrderDAO foodOrderDAO = new FoodOrderDAO();

	public AnalyticsController() {
	}

	// return a sorted linkedhashmap of all food from the canteen
	public LinkedHashMap<Food, Integer> topKfoods(int canteenId) {
		CanteenController canteenCtrl = new CanteenController();
		Canteen canteen = canteenCtrl.getCanteen(canteenId);

		ArrayList<OrderWindow> orderWindowList = orderWindowDAO.getAllWindowsForCanteen(canteen);
		ArrayList<FoodOrder> allFoodOrderList = new ArrayList<FoodOrder>();
		for (OrderWindow orderWindow : orderWindowList) {
			allFoodOrderList.addAll(foodOrderDAO.getAllFoodOrderOfOrderWindow(orderWindow));
		}

		LinkedHashMap<Food, Integer> foodCountMap = new LinkedHashMap<Food, Integer>();

		Set<FoodOrderItem> foodOrderItemSet = new HashSet<FoodOrderItem>();

		for (FoodOrder foodOrder : allFoodOrderList) {
			foodOrderItemSet.addAll(foodOrder.getFoodOrderList());
		}

		Iterator<FoodOrderItem> iter = foodOrderItemSet.iterator();
		while (iter.hasNext()) {
			FoodOrderItem foodOrderItem = (FoodOrderItem) iter.next();
			Food food = foodOrderItem.getFood();
			int count = 0;

			if (foodCountMap.containsKey(food)) {
				count = foodCountMap.get(food);
			}

			int quantity = foodOrderItem.getQuantity();
			count += quantity;
			foodCountMap.put(food, count);
		}

		// sort by integer
		List<Map.Entry<Food, Integer>> entries = new ArrayList<Map.Entry<Food, Integer>>(
				foodCountMap.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<Food, Integer>>() {
			public int compare(Map.Entry<Food, Integer> a, Map.Entry<Food, Integer> b) {
				return a.getValue().compareTo(b.getValue());
			}
		});

		LinkedHashMap<Food, Integer> sortedMap = new LinkedHashMap<Food, Integer>();
		for (Map.Entry<Food, Integer> entry : entries) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

}
