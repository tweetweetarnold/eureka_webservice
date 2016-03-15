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

import dao.FoodDAO;
import dao.FoodOrderDAO;
import dao.OrderPeriodDAO;
import model.Canteen;
import model.Food;
import model.FoodOrder;
import model.FoodOrderItem;
import model.OrderPeriod;

public class AnalyticsController {
	OrderPeriodDAO orderPeriodDAO = new OrderPeriodDAO();
	FoodOrderDAO foodOrderDAO = new FoodOrderDAO();
	FoodDAO foodDAO = new FoodDAO();

	public AnalyticsController() {
	}

	// return a sorted linkedhashmap of all food from the canteen
	public LinkedHashMap<Food, Integer> topKfoods(int canteenId) {
		CanteenController canteenCtrl = new CanteenController();
		Canteen canteen = canteenCtrl.getCanteen(canteenId);

		ArrayList<OrderPeriod> orderPeriodList = orderPeriodDAO.getAllPeriodsForCanteen(canteen);
		ArrayList<FoodOrder> allFoodOrderList = new ArrayList<FoodOrder>();
		for (OrderPeriod orderPeriod : orderPeriodList) {
			allFoodOrderList.addAll(foodOrderDAO.getAllFoodOrderOfOrderPeriod(orderPeriod));
		}
		List<Food> allFoodFromCanteen = foodDAO.getAllFoodFromCanteen(canteen);
		LinkedHashMap<Food, Integer> foodCountMap = new LinkedHashMap<Food, Integer>();

		Set<FoodOrderItem> foodOrderItemSet = new HashSet<FoodOrderItem>();

		for (FoodOrder foodOrder : allFoodOrderList) {
			foodOrderItemSet.addAll(foodOrder.getFoodOrderList());
		}
		for (Food f : allFoodFromCanteen) {
			int foodIDf = f.getFoodId();
			int count = 0;
			Iterator<FoodOrderItem> iter = foodOrderItemSet.iterator();
			while (iter.hasNext()) {
				FoodOrderItem foodOrderItem = (FoodOrderItem) iter.next();
				Food food = foodOrderItem.getFood();
				int foodID = food.getFoodId();
				if (foodID == foodIDf) {
					count += foodOrderItem.getQuantity();
				}

				// if (foodCountMap.containsKey(food)) {
				// count = foodCountMap.get(food);
				// }
				//
				// int quantity = foodOrderItem.getQuantity();
				// count += quantity;
				// foodCountMap.put(food, count);
			}
			if (foodCountMap.containsKey(f)) {
				count += foodCountMap.get(f);
				foodCountMap.put(f, count);
			} else {
				foodCountMap.put(f, count);
			}
		}

		// sort by integer
		List<Map.Entry<Food, Integer>> entries = new ArrayList<Map.Entry<Food, Integer>>(foodCountMap.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<Food, Integer>>() {
			public int compare(Map.Entry<Food, Integer> a, Map.Entry<Food, Integer> b) {
				return b.getValue().compareTo(a.getValue());
			}
		});

		LinkedHashMap<Food, Integer> sortedMap = new LinkedHashMap<Food, Integer>();
		for (Map.Entry<Food, Integer> entry : entries) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

}
