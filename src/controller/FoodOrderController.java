package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
import dao.FoodOrderItemDAO;

public class FoodOrderController {
	FoodOrderDAO foodOrderDAO = new FoodOrderDAO();
	EmployeeDAO employeeDAO = new EmployeeDAO();
	DateTime today = new DateTime();

	public FoodOrderController() {
	}

	public void addFoodOrder(FoodOrder f) {
		foodOrderDAO.saveFoodOrder(f);
	}

	public void updateFoodOrder(FoodOrder f) {
		foodOrderDAO.updateFoodOrder(f);
	}

	public List<FoodOrder> getFoodOrderSet(String email) {
		return foodOrderDAO.getFoodOrderSet(employeeDAO.getEmployeeByEmail(email));
	}

	// Retrieve FoodOrders between earlierDate and laterDate
	public List<FoodOrder> getFoodOrderBetweenCutOff(Date earlierDate, Date laterDate) {
		return foodOrderDAO.getFoodOrderByDate(earlierDate, laterDate);
	}

	// Retrieve a FoodOrder by id
	public FoodOrder getFoodOrder(int foodOrderId) {
		return foodOrderDAO.getFoodOrder(foodOrderId);
	}

	public List<FoodOrder> getUserFoodOrdersByStatus(String email, String status) {
		List<FoodOrder> returnList = new ArrayList<FoodOrder>();
		List<FoodOrder> foodOrderList = foodOrderDAO.getFoodOrderSet(employeeDAO
				.getEmployeeByEmail(email));

		for (FoodOrder f : foodOrderList) {
			if (f.getStatus().equals(status)) {
				returnList.add(f);
			}
		}
		return returnList;
	}

	// retrieve an ArrayList of FoodOrders made by a user in a week. sundayDate should be the
	// starting sunday for the week of orders you wish to retrieve. (Currently used by
	// viewWeeklyConsolidatedPaymentServlet.java)
	public ArrayList<FoodOrder> getFoodOrderForUsernameWeek(String email, Date sundayDate) {
		ArrayList<FoodOrder> foodOrderList = new ArrayList<FoodOrder>();
		Calendar cal = Calendar.getInstance();
		// this is to set the time of the sundayDate to 10AM
		sundayDate.setHours(10);
		sundayDate.setMinutes(0);
		cal.setTime(sundayDate);

		// this advances the calendar by 7 days. We need this in order to get the laterDate
		cal.add(Calendar.DATE, 7);
		Date laterDate = cal.getTime();
		System.out.println("Start date: " + sundayDate + " End date: " + laterDate);
		// retrieving the employee whose food order history we want to examine
		Employee tempEmployee = employeeDAO.getEmployeeByEmail(email);
		foodOrderList = foodOrderDAO
				.getFoodOrderByDateUsername(sundayDate, laterDate, tempEmployee);

		return foodOrderList;
	}

	// this is to get the FoodDisplayObject for displaying food orders for the day filtered by
	// stores each food display object corresponds to one store
	public ArrayList<FoodDisplayObject> getFoodOrderForCutOff(Date earlierDate, Date laterDate) {
		
		// retrieve the FoodOrders from yesterday 10am to today 10am
		ArrayList<FoodOrder> tempFoodOrderList = new ArrayList<FoodOrder>(
				getFoodOrderBetweenCutOff(earlierDate, laterDate));
		// This is what we will eventually return. (FINAL)
		ArrayList<FoodDisplayObject> foodDisplayList = new ArrayList<FoodDisplayObject>();

		// (A) LinkedHashMap for all the FoodOrderItems with the store as the key
		HashMap<String, ArrayList<FoodOrderItem>> stallToFoodItemLinkedHash = new HashMap<String, ArrayList<FoodOrderItem>>();

		// retrieving all the FoodOrderItems from a FoodOrder in order to populate the
		// (A)stallToFoodItemLinkedHash
		for (int i = 0; i < tempFoodOrderList.size(); i++) {
			FoodOrder tempFoodOrder = tempFoodOrderList.get(i);
			ArrayList<FoodOrderItem> tempFoodOrderItem = new ArrayList<FoodOrderItem>(
					tempFoodOrder.getFoodOrderList());

			// looping through the FoodOrderItems in the tempFoodOrderItemList and then checking
			// if the stall of the FoodOrderItem already exists in (A)stallToFoodItemLinkedHash
			// if it does then add the foodOrderItem into the existing list with the stall as the
			// key in (A)
			// else create a new Stall Key in (A)
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

		// Iterate through the stallToFoodItemLinkedHash
		Iterator iter = stallToFoodItemLinkedHash.keySet().iterator();

		int count = 1;
		// This will populate the (FINAL) list
		while (iter.hasNext()) {
			// this (B)LinkedHashMap will store all the users who ordered the particular
			// FoodOrderItem.
			HashMap<Integer, ArrayList<String>> usernamesForFoodItem = new HashMap<Integer, ArrayList<String>>();
			String stallName = (String) iter.next();
			// (AA)This holds all the FoodOrderItems for the stall.
			ArrayList<FoodOrderItem> tempFoodOrderItemForDisplay = stallToFoodItemLinkedHash
					.get(stallName);
			// this (C)LinkedHashMap will store the quantity of the particular FoodOrderItem.
			HashMap<Integer, Integer> quantityForFoodOrderItem = new HashMap<Integer, Integer>();
			ArrayList<FoodOrderItem> uniqueFoodOrderItem = new ArrayList<FoodOrderItem>();
			// this loops Through the foodOrderItems in (AA) in order to take out the unique
			// FoodOrderItems and stores them in UniqueFoodOrderItem
			for (FoodOrderItem i : tempFoodOrderItemForDisplay) {
				Iterator iterator = uniqueFoodOrderItem.iterator();
				int equalCount = 0;
				while (iterator.hasNext()) {
					FoodOrderItem tempFoodOrderItem = (FoodOrderItem) iterator.next();
					if (tempFoodOrderItem.equals2(i)) {
						equalCount++;
					}
				}
				if (equalCount == 0) {
					uniqueFoodOrderItem.add(i);
				}
			}

			// this populates (C) (Quantity)
			for (FoodOrderItem tempItem : uniqueFoodOrderItem) {
				int tempquantity = 0;
				for (FoodOrderItem i : tempFoodOrderItemForDisplay) {
					if (i.equals2(tempItem)) {
						tempquantity++;
					}
				}

				quantityForFoodOrderItem.put(tempItem.getFoodOrderItemId(), tempquantity);
			}

			// not too sure if we can use the old uniqueFoodOrderItemList....
			ArrayList<FoodOrderItem> uniqueFoodOrderItems = new ArrayList<FoodOrderItem>();
			Iterator iterFoodItemID = quantityForFoodOrderItem.keySet().iterator();
			FoodOrderItemDAO foodOrderItemDAO = new FoodOrderItemDAO();
			while (iterFoodItemID.hasNext()) {
				int id = (Integer) iterFoodItemID.next();
				FoodOrderItem tempFoodOrderItem = foodOrderItemDAO.getFoodOrderItem(id);
				uniqueFoodOrderItems.add(tempFoodOrderItem);
			}

			// Populating (B) (Users)
			for (FoodOrderItem f : uniqueFoodOrderItems) {
				HashSet<String> usernames = new HashSet<String>();
				for (FoodOrderItem i : tempFoodOrderItemForDisplay) {
					if (f.equals2(i)) {
						String tempUsername = i.getFoodOrder().getEmployee().getEmail() +" (" + i.getFoodOrder().getEmployee().getDefaultDeliveryPoint() + ")";
						usernames.add(tempUsername);
					}

					usernamesForFoodItem.put(f.getFoodOrderItemId(), new ArrayList<String>(
							usernames));
				}
			}
			// Populating the price for the FoodOrderItem
			HashMap<Integer, Double> foodOrderItemPrices = new HashMap<Integer, Double>();
			for (FoodOrderItem f : uniqueFoodOrderItems) {
				double tempPrice = f.getPrice();
				tempPrice = quantityForFoodOrderItem.get(f.getFoodOrderItemId()) * tempPrice;
				foodOrderItemPrices.put(f.getFoodOrderItemId(), tempPrice);
			}

			// Calculating the total price
			Iterator totalPriceIterator = foodOrderItemPrices.keySet().iterator();
			double totalPrice = 0.0;
			while (totalPriceIterator.hasNext()) {
				totalPrice += (double) foodOrderItemPrices.get(totalPriceIterator.next());
			}

			// setting the telephone number
			Long phone = uniqueFoodOrderItem.get(0).getFood().getStall().getContactNo();

			// Each FoodDisplayObject represents one stall
			FoodDisplayObject tempFoodDisplay = new FoodDisplayObject(count++);
			tempFoodDisplay.setStallName(stallName);
			tempFoodDisplay.setFoodOrderItemList(uniqueFoodOrderItems);
			tempFoodDisplay.setUsernameList(usernamesForFoodItem);
			tempFoodDisplay.setQuantityList(quantityForFoodOrderItem);
			tempFoodDisplay.setPriceList(foodOrderItemPrices);
			tempFoodDisplay.setTotalPrice(totalPrice);
			tempFoodDisplay.setPhoneNumber(phone);
			foodDisplayList.add(tempFoodDisplay);
		}
		return foodDisplayList;
	}

	public HashMap<Integer, ArrayList<FoodOrderItem>> getFoodOrderItemsForStall(Date earlierDate,
			Date laterDate) {
		// ArrayList<FoodOrder> tempFoodOrderList = new ArrayList<FoodOrder>(
		// getFoodOrderBetweenCutOff(earlierDate, laterDate));
		List<FoodOrder> tempFoodOrderList = foodOrderDAO.getFoodOrderByDate(today.minusDays(1)
				.toDate(), today.toDate());

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

	public HashMap<String, ArrayList<FoodOrderItem>> getFoodOrderToday() {
		// get all orders made today
		FoodOrderDAO foodOrderDAO = new FoodOrderDAO();

		List<FoodOrder> tempFoodOrderList = foodOrderDAO.getFoodOrderByDate(today.minusDays(1)
				.toDate(), today.toDate());

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
		}
		return map;
	}
}
