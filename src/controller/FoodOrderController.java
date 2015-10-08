package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import model.Employee;
import model.Food;
import model.FoodDisplayObject;
import model.FoodOrder;
import model.FoodOrderItem;
import model.Stall;

import org.joda.time.JodaTimePermission;

import com.google.gson.Gson;

import dao.FoodOrderDAO;

public class FoodOrderController {
	FoodOrderDAO foodOrderDAO = new FoodOrderDAO();

	public FoodOrderController() {

	}

	public void addFoodOrder(FoodOrder f) {
		foodOrderDAO.saveFoodOrder(f);
	}

	// Retrieve a FoodOrder by id
	public FoodOrder getFoodOrder(int foodOrderId) {
		return foodOrderDAO.getFoodOrder(foodOrderId);
	}

	// Retrieve all FoodOrders from yesterday 10:00:00 to today 10:00:00 with
	// total price as object in HashMap with key "totalPrice"
	public ArrayList<FoodDisplayObject> getFoodOrderforCutOff() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(cal.getTime());
		System.out.println("Current date in String Format: " + strDate);
		SimpleDateFormat sdf1 = new SimpleDateFormat();
		sdf1.applyPattern("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf1.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String string = sdf1.format(date);
		System.out.println("Current date in Date Format: " + string);
		cal.add(Calendar.DATE, -1);
		String dateAfter = sdf1.format(cal.getTime());

		System.out.println(string);
		ArrayList<FoodOrder> tempFoodOrderList = new ArrayList<FoodOrder>(
				foodOrderDAO.getFoodOrderByDateAndTime(dateAfter + " 10:00:00", string + " 10:00:00"));
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
			HashMap<FoodOrderItem , ArrayList<String>> usernamesForFoodItem = new HashMap<FoodOrderItem, ArrayList<String>>();
			String stallName = (String) iter.next();
			ArrayList<FoodOrderItem> tempFoodOrderItemForDisplay = stallToFoodItemLinkedHash.get(stallName);
			for(FoodOrderItem i :tempFoodOrderItemForDisplay){
				ArrayList<String> usernames = new ArrayList<String>();
				FoodOrder tempFoodOrderToDisplay = i.getFoodOrder();
				String username = tempFoodOrderToDisplay.getEmployee().getUsername();
				usernames.add(username);
				if(!usernamesForFoodItem.containsKey(i)){
					usernamesForFoodItem.put(i, usernames);
				}else{
					ArrayList<String> newUsernamesInput = usernamesForFoodItem.get(i);
					newUsernamesInput.addAll(usernames);
					usernamesForFoodItem.put(i, newUsernamesInput);
				}
				
			}
			
			FoodDisplayObject tempFoodDisplay = new FoodDisplayObject(count++);
			tempFoodDisplay.setStallName(stallName);
			tempFoodDisplay.setFoodOrderItem(tempFoodOrderItemForDisplay);
			tempFoodDisplay.setUsername(usernamesForFoodItem);
			tempFoodDisplay.setQuantity();
			foodDisplayList.add(tempFoodDisplay);
		}

		// foodOrderItems With Employee name as key
		// HashMap foodOrders = new HashMap();
		// Iterator iter = tempFoodOrderList.iterator();
		// double totalPrice = 0.0;
		// while (iter.hasNext()) {
		// FoodOrder tempFoodOrder = (FoodOrder) iter.next();
		// ArrayList<FoodOrderItem> foodOrderList = new
		// ArrayList<FoodOrderItem>(tempFoodOrder.getFoodOrderList());
		// foodOrders.put(tempFoodOrder.getEmployee().getUsername(),
		// foodOrderList);
		// double price = tempFoodOrder.getFoodOrderTotalPrice();
		// totalPrice += price;
		// }
		// foodOrders.put("totalPrice", totalPrice);
		return foodDisplayList;
	}

	// Retrieve FoodOrders from today 00:00:00 to today 23:59:59 with total
	// price as object in HashMap with key "totalPrice"
	public HashMap getFoodOrderToday() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(cal.getTime());
		System.out.println("Current date in String Format: " + strDate);
		SimpleDateFormat sdf1 = new SimpleDateFormat();
		sdf1.applyPattern("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf1.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String string = sdf1.format(date);
		System.out.println("Current date in Date Format: " + string);

		System.out.println(string);
		List<FoodOrder> tempFoodOrderList = foodOrderDAO.getFoodOrderByDate(string);

		// foodOrderItems With Employee name as key
		HashMap foodOrders = new HashMap();
		Iterator iter = tempFoodOrderList.iterator();
		double totalPrice = 0.0;
		while (iter.hasNext()) {
			FoodOrder tempFoodOrder = (FoodOrder) iter.next();
			ArrayList<FoodOrderItem> foodOrderList = new ArrayList<FoodOrderItem>(tempFoodOrder.getFoodOrderList());
			Employee tempEmployee = tempFoodOrder.getEmployee();
			System.out.println(tempEmployee);
			if (tempEmployee != null) {
				foodOrders.put(tempFoodOrder.getEmployee().getUsername(), foodOrderList);
			} else {
				foodOrders.put("Unknown", foodOrderList);
			}
			double price = tempFoodOrder.getFoodOrderTotalPrice();
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
