package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import model.Employee;
import model.Food;
import model.FoodDisplayObject;
import model.FoodOrder;
import model.FoodOrderItem;
import model.Stall;
import dao.EmployeeDAO;
import dao.FoodOrderDAO;

public class FoodOrderController {
	FoodOrderDAO foodOrderDAO = new FoodOrderDAO();

	public FoodOrderController() {

	}

	public void addFoodOrder(FoodOrder f) {
		FoodOrderDAO.saveFoodOrder(f);
	}

	public List<FoodOrder> getFoodOrderSet(String username) {
		return FoodOrderDAO.getFoodOrderSet(EmployeeDAO.getEmployeeByUsername(username));
	}

	// Retrieve a FoodOrder by id
	public FoodOrder getFoodOrder(int foodOrderId) {
		return FoodOrderDAO.getFoodOrder(foodOrderId);
	}

	// Retrieve all FoodOrders from yesterday 10:00:00 to today 10:00:00 with
	// total price as object in HashMap with key "totalPrice"
	public ArrayList<FoodDisplayObject> getFoodOrderforCutOff() {

		ArrayList<FoodOrder> tempFoodOrderList = new ArrayList<FoodOrder>(getFoodOrderBetweenCutOff());
		System.out.println("Size from DAO:  " + tempFoodOrderList.size());
		ArrayList<FoodDisplayObject> foodDisplayList = new ArrayList<FoodDisplayObject>();
		LinkedHashMap<String, ArrayList<FoodOrderItem>> stallToFoodItemLinkedHash = new LinkedHashMap<String, ArrayList<FoodOrderItem>>();
		LinkedHashMap<String, ArrayList<String>> stallToUserLinkedHash = new LinkedHashMap<String, ArrayList<String>>();
		for (int i = 0; i < tempFoodOrderList.size(); i++) {
			FoodOrder tempFoodOrder = tempFoodOrderList.get(i);
			ArrayList<FoodOrderItem> tempFoodOrderItem = new ArrayList<FoodOrderItem>(tempFoodOrder.getFoodOrderList());
			for (FoodOrderItem foodItem : tempFoodOrderItem) {
				FoodOrderItem foodItemd = foodItem;
				Food tempFood = foodItemd.getFood();
				Stall tempStall = tempFood.getStall();
				ArrayList<FoodOrderItem> tempFoodOrderItemList = new ArrayList<FoodOrderItem>();
				tempFoodOrderItemList.add(foodItemd);
				ArrayList<String> tempUserList = new ArrayList<String>();
				tempUserList.add(tempFoodOrder.getEmployee().getUsername());
				String stallName = tempStall.getName();
				if (!stallToFoodItemLinkedHash.containsKey(stallName)) {
					stallToFoodItemLinkedHash.put(stallName, tempFoodOrderItemList);
				} else {
					ArrayList<FoodOrderItem> tempFoodOrderItemToCombi = stallToFoodItemLinkedHash.get(stallName);
					tempFoodOrderItemToCombi.addAll(tempFoodOrderItemList);
					stallToFoodItemLinkedHash.put(stallName, tempFoodOrderItemToCombi);
				}

			}

		}

		Iterator iter = stallToFoodItemLinkedHash.keySet().iterator();

		int count = 1;
		while (iter.hasNext()) {
			System.out.println("Number of Stalls" + stallToFoodItemLinkedHash.size());

			LinkedHashMap<FoodOrderItem, ArrayList<String>> usernamesForFoodItem = new LinkedHashMap<FoodOrderItem, ArrayList<String>>();
			String stallName = (String) iter.next();
			ArrayList<FoodOrderItem> tempFoodOrderItemForDisplay = stallToFoodItemLinkedHash.get(stallName);

			LinkedHashMap<FoodOrderItem, Integer> quantityForFoodOrderItem = new LinkedHashMap<FoodOrderItem, Integer>();
			for (FoodOrderItem i : tempFoodOrderItemForDisplay) {

				FoodOrderItem currentFoodOrderItem = i;
				int quantity = 0;
				Iterator uniqueIter = tempFoodOrderItemForDisplay.iterator();
				while (uniqueIter.hasNext()) {
					FoodOrderItem tempFoodOrderItem = (FoodOrderItem) uniqueIter.next();
					if (tempFoodOrderItem.equals(currentFoodOrderItem)) {
						quantity++;
						System.out.println("Quantity check: " + quantity);
					}
				}
				System.out.println("Quantity check: " + quantity);

				int equalsCount2 = 0;
				uniqueIter = quantityForFoodOrderItem.keySet().iterator();
				while (uniqueIter.hasNext()) {
					FoodOrderItem tempFoodOrderItem = (FoodOrderItem) uniqueIter.next();
					if (tempFoodOrderItem.equals(i)) {
						equalsCount2++;
					}
				}
				if (equalsCount2 == 0) {
					quantityForFoodOrderItem.put(i, quantity);
				}

			}

			ArrayList<FoodOrderItem> uniqueFoodOrderItems = new ArrayList<FoodOrderItem>(
					quantityForFoodOrderItem.keySet());

			for (FoodOrderItem f : uniqueFoodOrderItems) {
				ArrayList<String> usernames = new ArrayList<String>();
				for (FoodOrderItem i : tempFoodOrderItemForDisplay) {
					if (f.equals(i)) {
						String tempUsername = i.getFoodOrder().getEmployee().getUsername();
						usernames.add(tempUsername);
					}
					usernamesForFoodItem.put(f, usernames);
				}
			}
			for (FoodOrderItem s : uniqueFoodOrderItems) {
				System.out.println(s.getFood().getName());
			}
			FoodDisplayObject tempFoodDisplay = new FoodDisplayObject(count++);
			tempFoodDisplay.setStallName(stallName);
			tempFoodDisplay.setFoodOrderItem(uniqueFoodOrderItems);
			tempFoodDisplay.setUsername(usernamesForFoodItem);
			tempFoodDisplay.setQuantity(quantityForFoodOrderItem);
			foodDisplayList.add(tempFoodDisplay);
		}

		return foodDisplayList;
	}

	// Retrieve FoodOrders from today 00:00:00 to today 23:59:59 with total
	// price as object in HashMap with key "totalPrice"

	public List<FoodOrder> getFoodOrderBetweenCutOff() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date laterDate = cal.getTime();
		laterDate.setHours(10);
		cal.add(Calendar.DATE, -1);
		Date earlierDate = cal.getTime();
		earlierDate.setHours(10);
		Date nowDate = new Date();
		if (nowDate.after(laterDate)) {
			cal.setTime(laterDate);
			cal.add(Calendar.DATE, +1);
			laterDate = cal.getTime();
			cal.setTime(earlierDate);
			cal.add(Calendar.DATE, +1);
			earlierDate = cal.getTime();
		}

		System.out.println(earlierDate + " " + laterDate);

		return FoodOrderDAO.getFoodOrderByDate(laterDate, earlierDate);

	}

	public HashMap getFoodOrderToday() {
		List<FoodOrder> tempFoodOrderList = getFoodOrderBetweenCutOff();
		ArrayList<String> uniqueEmployee = new ArrayList<String>();
		// foodOrderItems With Employee name as key
		HashMap foodOrders = new HashMap();
		Iterator iter = tempFoodOrderList.iterator();
		double totalPrice = 0.0;
		while (iter.hasNext()) {
			FoodOrder tempFoodOrder = (FoodOrder) iter.next();
			ArrayList<FoodOrderItem> foodOrderList = new ArrayList<FoodOrderItem>(tempFoodOrder.getFoodOrderList());
			Employee tempEmployee = tempFoodOrder.getEmployee();
			String username = tempEmployee.getUsername();
			System.out.println(tempEmployee);
			if (tempEmployee != null) {

				if (!uniqueEmployee.contains(username)) {
					foodOrders.put(tempFoodOrder.getEmployee().getUsername(), foodOrderList);
					uniqueEmployee.add(username);
				}else{
					ArrayList<FoodOrderItem> tempFoodOrderItemList = (ArrayList<FoodOrderItem>) foodOrders.get(username);
					tempFoodOrderItemList.addAll(foodOrderList);
					foodOrders.put(username, tempFoodOrderItemList);
					System.out.println("Username: " + username + foodOrderList.size() );
				}
			} else {
				foodOrders.put("Unknown", foodOrderList);
			}
			double price = tempFoodOrder.getTotalPrice();
			totalPrice += price;
		}

		if (foodOrders.size() > 0) {
			foodOrders.put("totalPrice", totalPrice);
			return foodOrders;
		} else {
			return null;
		}
	}

}
