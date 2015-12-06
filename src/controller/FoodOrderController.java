package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import model.Employee;
import model.Food;
import model.FoodDisplayObject;
import model.FoodOrder;
import model.FoodOrderItem;
import model.Stall;

import org.joda.time.DateTime;

import dao.EmployeeDAO;
import dao.FoodOrderDAO;

public class FoodOrderController {
	FoodOrderDAO foodOrderDAO = new FoodOrderDAO();
	EmployeeDAO employeeDAO = new EmployeeDAO();

	public FoodOrderController() {
	}

	public void addFoodOrder(FoodOrder f) {
		foodOrderDAO.saveFoodOrder(f);
	}

	public List<FoodOrder> getFoodOrderSet(String email) {
		return foodOrderDAO.getFoodOrderSet(employeeDAO.getEmployeeByEmail(email));
	}

	// Retrieve a FoodOrder by id
	public FoodOrder getFoodOrder(int foodOrderId) {
		return foodOrderDAO.getFoodOrder(foodOrderId);
	}

	public ArrayList<FoodOrder> getFoodOrderForUsernameWeek(String email, Date sundayDate) {
		ArrayList<FoodOrder> foodOrderList = new ArrayList<FoodOrder>();
		Calendar cal = Calendar.getInstance();
		sundayDate.setHours(10);
		sundayDate.setMinutes(0);
		cal.setTime(sundayDate);
		cal.add(Calendar.DATE, 7);
		Date laterDate = cal.getTime();
		System.out.println("Start date: " + sundayDate + " End date: " + laterDate);
		Employee tempEmployee = employeeDAO.getEmployeeByEmail(email);
		foodOrderList = foodOrderDAO
				.getFoodOrderByDateUsername(sundayDate, laterDate, tempEmployee);
		System.out.println(foodOrderList.size() + foodOrderList.get(0).getEmployee().getEmail());
		System.out.println(foodOrderList.get(0).getFoodOrderList().size());

		return foodOrderList;
	}

	// Retrieve all FoodOrders from yesterday 10:00:00 to today 10:00:00 with
	// total price as object in HashMap with key "totalPrice"
	public ArrayList<FoodDisplayObject> getFoodOrderforCutOff() {

		ArrayList<FoodOrder> tempFoodOrderList = new ArrayList<FoodOrder>(
				getFoodOrderBetweenCutOff());
		System.out.println("Size from DAO:  " + tempFoodOrderList.size());
		ArrayList<FoodDisplayObject> foodDisplayList = new ArrayList<FoodDisplayObject>();
		LinkedHashMap<String, ArrayList<FoodOrderItem>> stallToFoodItemLinkedHash = new LinkedHashMap<String, ArrayList<FoodOrderItem>>();
		LinkedHashMap<String, ArrayList<String>> stallToUserLinkedHash = new LinkedHashMap<String, ArrayList<String>>();
		for (int i = 0; i < tempFoodOrderList.size(); i++) {
			FoodOrder tempFoodOrder = tempFoodOrderList.get(i);
			ArrayList<FoodOrderItem> tempFoodOrderItem = new ArrayList<FoodOrderItem>(
					tempFoodOrder.getFoodOrderList());
			for (FoodOrderItem foodItem : tempFoodOrderItem) {
				FoodOrderItem foodItemd = foodItem;
				Food tempFood = foodItemd.getFood();
				Stall tempStall = tempFood.getStall();
				ArrayList<FoodOrderItem> tempFoodOrderItemList = new ArrayList<FoodOrderItem>();
				tempFoodOrderItemList.add(foodItemd);
				ArrayList<String> tempUserList = new ArrayList<String>();
				tempUserList.add(tempFoodOrder.getEmployee().getEmail());
				String stallName = tempStall.getName();
				if (!stallToFoodItemLinkedHash.containsKey(stallName)) {
					stallToFoodItemLinkedHash.put(stallName, tempFoodOrderItemList);
				} else {
					ArrayList<FoodOrderItem> tempFoodOrderItemToCombi = stallToFoodItemLinkedHash
							.get(stallName);
					tempFoodOrderItemToCombi.addAll(tempFoodOrderItemList);
					stallToFoodItemLinkedHash.put(stallName, tempFoodOrderItemToCombi);
				}

			}

		}

		Iterator iter = stallToFoodItemLinkedHash.keySet().iterator();

		int count = 1;
		while (iter.hasNext()) {

			LinkedHashMap<FoodOrderItem, ArrayList<String>> usernamesForFoodItem = new LinkedHashMap<FoodOrderItem, ArrayList<String>>();
			String stallName = (String) iter.next();
			ArrayList<FoodOrderItem> tempFoodOrderItemForDisplay = stallToFoodItemLinkedHash
					.get(stallName);
			System.out.println("Number of initial FoodOrderItems :))))))))))):"
					+ tempFoodOrderItemForDisplay.size());
			LinkedHashMap<FoodOrderItem, Integer> quantityForFoodOrderItem = new LinkedHashMap<FoodOrderItem, Integer>();
			ArrayList<FoodOrderItem> uniqueFoodOrderItem = new ArrayList<FoodOrderItem>();
			for (FoodOrderItem i : tempFoodOrderItemForDisplay) {

				Iterator iterator = uniqueFoodOrderItem.iterator();
				int equalCount = 0;
				while (iterator.hasNext()) {
					FoodOrderItem tempFoodOrderItem = (FoodOrderItem) iterator.next();
					if (tempFoodOrderItem.equals2(i)) {
						equalCount++;
						System.out.println("FUCK");
					}
				}
				if (equalCount == 0) {
					uniqueFoodOrderItem.add(i);
				}

			}

			for (FoodOrderItem tempItem : uniqueFoodOrderItem) {
				int tempquantity = 0;
				for (FoodOrderItem i : tempFoodOrderItemForDisplay) {
					if (i.equals2(tempItem)) {
						tempquantity++;
					}
				}
				quantityForFoodOrderItem.put(tempItem, tempquantity);
			}

			ArrayList<FoodOrderItem> uniqueFoodOrderItems = new ArrayList<FoodOrderItem>(
					quantityForFoodOrderItem.keySet());

			for (FoodOrderItem f : uniqueFoodOrderItems) {
				HashSet<String> usernames = new HashSet<String>();
				for (FoodOrderItem i : tempFoodOrderItemForDisplay) {
					if (f.equals2(i)) {
						String tempUsername = i.getFoodOrder().getEmployee().getEmail();
						usernames.add(tempUsername);
					}
					usernamesForFoodItem.put(f, new ArrayList<String>(usernames));
				}
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
		laterDate.setMinutes(0);
		cal.add(Calendar.DATE, -1);
		Date earlierDate = cal.getTime();
		earlierDate.setHours(10);
		earlierDate.setMinutes(0);
		System.out.println("earlier Later : " + laterDate);

		Date fivePm = new Date();
		fivePm.setHours(17);
		fivePm.setMinutes(00);
		Date nowDate = new Date();
		nowDate.setMinutes(0);
		System.out.println("Later : " + laterDate);

		if (nowDate.after(fivePm)) {
			cal.setTime(laterDate);
			cal.add(Calendar.DATE, +1);
			laterDate = cal.getTime();
			cal.setTime(earlierDate);
			cal.add(Calendar.DATE, +1);
			earlierDate = cal.getTime();
		}
		return foodOrderDAO.getFoodOrderByDate(laterDate, earlierDate);
	}

	public HashMap<Integer, ArrayList<FoodOrderItem>> getFoodOrderItemsForStall() {
		ArrayList<FoodOrder> tempFoodOrderList = new ArrayList<FoodOrder>(
				getFoodOrderBetweenCutOff());
		ArrayList<FoodOrderItem> allFoodOrderItems = new ArrayList<FoodOrderItem>();
		HashSet<Integer> uniqueStallNames = new HashSet<Integer>();
		HashMap<Integer, ArrayList<FoodOrderItem>> mapToReturn = new HashMap<Integer, ArrayList<FoodOrderItem>>();
		for (FoodOrder tempFoodOrder : tempFoodOrderList) {
			allFoodOrderItems.addAll(tempFoodOrder.getFoodOrderList());
		}
		System.out.print("Number of FoodOrderItems: " + allFoodOrderItems.size());
		for (FoodOrderItem tempFoodOrderItem : allFoodOrderItems) {
			Stall tempStall = tempFoodOrderItem.getFood().getStall();
			Integer tempStallName = tempStall.getStallId();
			uniqueStallNames.add(tempStallName);
		}
		System.out.print("Number of Unique Stalls: " + allFoodOrderItems.size());
		for (Integer tempStallID : uniqueStallNames) {
			ArrayList<FoodOrderItem> foodOrderListForStallName = new ArrayList<FoodOrderItem>();
			for (FoodOrderItem tempFoodOrderItem : allFoodOrderItems) {
				Integer tempFoodOrderItemStallID = tempFoodOrderItem.getFood().getStall()
						.getStallId();
				if (tempFoodOrderItemStallID == tempStallID) {
					foodOrderListForStallName.add(tempFoodOrderItem);
				}
			}
			mapToReturn.put(tempStallID, foodOrderListForStallName);
		}

		return mapToReturn;
	}

	public HashMap getFoodOrderToday() {
		List<FoodOrder> tempFoodOrderList = getFoodOrderBetweenCutOff();

		ArrayList<String> uniqueEmployee = new ArrayList<String>();

		// foodOrderItems With Employee name as key
		HashMap<String, ArrayList<FoodOrderItem>> foodOrders = new HashMap<String, ArrayList<FoodOrderItem>>();

		Iterator iter = tempFoodOrderList.iterator();
		double totalPrice = 0.0;

		while (iter.hasNext()) {
			FoodOrder tempFoodOrder = (FoodOrder) iter.next();
			ArrayList<FoodOrderItem> foodOrderList = new ArrayList<FoodOrderItem>(
					tempFoodOrder.getFoodOrderList());
			Employee tempEmployee = tempFoodOrder.getEmployee();
			String email = tempEmployee.getEmail();
			System.out.println(tempEmployee);
			if (tempEmployee != null) {

				if (!uniqueEmployee.contains(email)) {
					foodOrders.put(tempFoodOrder.getEmployee().getEmail(), foodOrderList);
					uniqueEmployee.add(email);
				} else {
					ArrayList<FoodOrderItem> tempFoodOrderItemList = (ArrayList<FoodOrderItem>) foodOrders
							.get(email);
					tempFoodOrderItemList.addAll(foodOrderList);
					foodOrders.put(email, tempFoodOrderItemList);
					System.out.println("Email: " + email + foodOrderList.size());
				}
			} else {
				foodOrders.put("Unknown", foodOrderList);
			}
			double price = tempFoodOrder.getTotalPrice();
			totalPrice += price;
		}

		// if (foodOrders.size() > 0) {
		// foodOrders.put("totalPrice", totalPrice);
		// return foodOrders;
		// } else {
		// return null;
		// }
		return null;
	}

	public HashMap<String, ArrayList<FoodOrderItem>> getFoodOrderToday2() {
		// get all orders made today
		FoodOrderDAO foodOrderDAO = new FoodOrderDAO();
		DateTime today = new DateTime();

		List<FoodOrder> tempFoodOrderList = foodOrderDAO.getFoodOrderByDate(today.minusDays(1)
				.toDate(), today.toDate());
		System.out.println("Tempfoodorderlist size: " + tempFoodOrderList.size());

		// hashmap for return later
		HashMap<String, ArrayList<FoodOrderItem>> map = new HashMap<String, ArrayList<FoodOrderItem>>();

		// iterate through each foodorder from tempfoodorderlist and add it into hashmap. if
		// already have record of existing user, add to that same list
		for (FoodOrder o : tempFoodOrderList) {
			String email = o.getEmployee().getEmail();

			// check if entry exists. if not, create new arraylist and add into map
			ArrayList<FoodOrderItem> tempItems = map.get(email);
			if (tempItems == null) {
				tempItems = new ArrayList<FoodOrderItem>();
			}
			tempItems.addAll(o.getFoodOrderList());
			map.put(email, tempItems);
			System.out.println("Tempitems size: " + tempItems.size());

		}
		return map;
	}
}
