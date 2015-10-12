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

import model.Admin;
import model.Canteen;
import model.Company;
import model.Employee;
import model.Food;
import model.FoodDisplayObject;
import model.FoodOrder;
import model.FoodOrderItem;
import model.ModifierChosen;
import model.Stall;
import services.PasswordService;
import value.StringValues;

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

//		System.out.println(string);
//		// Start of test
//		ArrayList<FoodOrder> testFoodOrderList = new ArrayList<FoodOrder>();
//		Canteen canteen = new Canteen("Default Canteen", "123", new Date(), null);
//		Stall stall = new Stall("stall123", "123", "Default Stall", 123, canteen, new Date(), null);
//		Food food = new Food("Arnold's Fried Chicken", "Quite Nice", 2.50, stall, new Date());
//
//		Admin admin = new Admin("admin", PasswordService.encryptPassword("1234567"), "admin123", 123456789, new Date());
//		Company company = new Company("XiaoDingDang Co.", null, new Date(), null, null);
//		Employee employee = new Employee("arnold", PasswordService.encryptPassword("1234567"), "arnold", 999999999, 10,
//				123, company, null, null, new Date());
//		FoodOrder order = new FoodOrder(StringValues.ORDER_CONFIRMED, employee, admin, null, new Date());
//		FoodOrderItem foodItem1 = new FoodOrderItem(order, food, 1, "More meat", new Date());
//		HashSet<ModifierChosen> modifierList = new HashSet<ModifierChosen>();
//		ModifierChosen modifierChosen = new ModifierChosen("Upsize", "Upsize Arnold", 0.50, foodItem1, new Date());
//		modifierList.add(modifierChosen);
//		foodItem1.setModifierList(modifierList);
//		HashSet<FoodOrderItem> foodOrderList = new HashSet<FoodOrderItem>();
//		foodOrderList.add(foodItem1);
//		order.setFoodOrderList(foodOrderList);
//		testFoodOrderList.add(order);
//		// 2nd employee Chris same order
//		Employee employee2 = new Employee("Chris", PasswordService.encryptPassword("1234567"), "Chris", 999999999, 10,
//				123, company, null, null, new Date());
//		FoodOrder order2 = new FoodOrder(StringValues.ORDER_CONFIRMED, employee2, admin, null, new Date());
//		FoodOrderItem foodItem2 = new FoodOrderItem(order2, food, 1, "More meat", new Date());
//		HashSet<ModifierChosen> modifierList2 = new HashSet<ModifierChosen>();
//		ModifierChosen modifierChosen2 = new ModifierChosen("Upsize", "Upsize Arnold", 0.50, foodItem2, new Date());
//		modifierList2.add(modifierChosen2);
//		foodItem2.setModifierList(modifierList2);
//		HashSet<FoodOrderItem> foodOrderList2 = new HashSet<FoodOrderItem>();
//		foodOrderList2.add(foodItem2);
//		order2.setFoodOrderList(foodOrderList2);
//		testFoodOrderList.add(order2);
//		// 3rd employee different foodOrder
//		Food food3 = new Food("FANNYS Fried Chicken", "Quite Nice", 2.50, stall, new Date());
//		Employee employee3 = new Employee("Angelia", PasswordService.encryptPassword("1234567"), "Angelia", 999999999,
//				10, 123, company, null, null, new Date());
//		FoodOrder order3 = new FoodOrder(StringValues.ORDER_CONFIRMED, employee3, admin, null, new Date());
//		FoodOrderItem foodItem3 = new FoodOrderItem(order3, food3, 1, "More meat", new Date());
//		HashSet<ModifierChosen> modifierList3 = new HashSet<ModifierChosen>();
//		ModifierChosen modifierChosen3 = new ModifierChosen("Upsize", "Upsize Arnold", 0.50, foodItem3, new Date());
//		modifierList3.add(modifierChosen3);
//		foodItem3.setModifierList(modifierList3);
//		HashSet<FoodOrderItem> foodOrderList3 = new HashSet<FoodOrderItem>();
//		foodOrderList3.add(foodItem3);
//		order3.setFoodOrderList(foodOrderList3);
//		testFoodOrderList.add(order3);

//		ArrayList<FoodOrder> tempFoodOrderList = testFoodOrderList;

		 ArrayList<FoodOrder> tempFoodOrderList = new
		 ArrayList<FoodOrder>(foodOrderDAO.getFoodOrderByDateAndTime(dateAfter
		 + " 10:00:00", string + " 10:00:00"));
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

				// FoodOrder tempFoodOrderToDisplay = i.getFoodOrder();
				// String username =
				// tempFoodOrderToDisplay.getEmployee().getUsername();
				//
				//
				// int equalsCount1 = 0;
				// Iterator uniqueIter =
				// usernamesForFoodItem.keySet().iterator();
				// while (uniqueIter.hasNext()) {
				// FoodOrderItem tempFoodOrderItem = (FoodOrderItem)
				// uniqueIter.next();
				// if (tempFoodOrderItem.equals(i)) {
				// equalsCount1++;
				// }
				// }
				// if (equalsCount1 == 0) {
				// usernamesForFoodItem.put(i, usernames);
				// }

				// FoodOrder tempFoodOrderToDisplay = i.getFoodOrder();
				// String username =
				// tempFoodOrderToDisplay.getEmployee().getUsername();
				// usernames.add(username);
				// if (!usernamesForFoodItem.containsKey(i)) {
				// usernamesForFoodItem.put(i, usernames);
				// } else {
				// ArrayList<String> newUsernamesInput =
				// usernamesForFoodItem.get(i);
				// newUsernamesInput.addAll(usernames);
				// usernamesForFoodItem.put(i, newUsernamesInput);
				// }

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
					if(f.equals(i)){
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
