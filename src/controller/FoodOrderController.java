package controller;

import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.joda.time.DateTime;

import connection.MyConnection;
import dao.EmployeeDAO;
import dao.FoodOrderDAO;
import dao.FoodOrderItemDAO;
import dao.ModifierSectionDAO;
import model.Company;
import model.Employee;
import model.Food;
import model.FoodDisplayObject;
import model.FoodOrder;
import model.FoodOrderItem;
import model.ModifierChosen;
import model.OrderPeriod;
import model.Stall;
import value.StringValues;

/**
 * Process the business logic of managing the Food orders for the web application
 * 
 * @author SMU Team Eureka
 * 
 */
public class FoodOrderController {
	EmployeeDAO employeeDAO = new EmployeeDAO();
	FoodOrderDAO foodOrderDAO = new FoodOrderDAO();
	DateTime today = new DateTime();
	FoodOrderItemDAO foodOrderItemDAO = new FoodOrderItemDAO();
	ModifierSectionDAO modifierSectionDAO = new ModifierSectionDAO();
	CompanyController companyController = new CompanyController();

	/**
	 * Creates a default constructor for FoodOrderController
	 */
	public FoodOrderController() {
	}

	/**
	 * Add a new FoodOrder into Database
	 * 
	 * @param f The FoodOrder object to be added to Database
	 */
	public void addFoodOrder(FoodOrder f) {
		foodOrderDAO.saveFoodOrder(f);
	}

	public void addFoodOrderItemIntoFoodOrder(FoodOrderItem foodOrderItem, FoodOrder foodOrder) {
		Employee employee = foodOrder.getEmployee();
		double amountOwed = employee.getAmountOwed();
		amountOwed -= foodOrder.getFinalPrice();
		Set<FoodOrderItem> foodOrderItemList = foodOrder.getFoodOrderList();
		foodOrderItemList.add(foodOrderItem);
		foodOrder.setFoodOrderList(foodOrderItemList);
		amountOwed += foodOrder.getFinalPrice();
		employee.setAmountOwed(amountOwed);
		employeeDAO.updateEmployee(employee);
		updateFoodOrder(foodOrder);
	}

	public boolean checkForExistingOrder(Employee employee, OrderPeriod orderPeriod) {
		if (foodOrderDAO.getAllFoodOrderOfOrderPeriodForUser(employee, orderPeriod).isEmpty()) {
			return false;
		}
		return true;
	}

	public void deleteFoodOrderItem(int foodOrderItemId) throws Exception {

		FoodOrderItem foodOrderItemToDelete = foodOrderItemDAO.getFoodOrderItem(foodOrderItemId);
		FoodOrder foodOrderToDelete = foodOrderItemToDelete.getFoodOrder();
		if (foodOrderToDelete.getStatus() == StringValues.PAID) {
			throw new Exception("The food has already been paid for. Cannot delete Food Order.");
		}
		Employee employee = foodOrderToDelete.getEmployee();
		double amountOwed = employee.getAmountOwed();
		double newAmountOwed = amountOwed - foodOrderItemToDelete.getTotalPrice();
		employee.setAmountOwed(newAmountOwed);

		// update employee, foodOrderItem and foodOrder
		employeeDAO.updateEmployee(employee);

		// update FoodOrder (Remove FoodOrderItem)
		foodOrderToDelete.getFoodOrderList().remove(foodOrderItemToDelete);
		updateFoodOrder(foodOrderToDelete);

		// remove ModifierChosen
		Set<ModifierChosen> modifierList = foodOrderItemToDelete.getModifierChosenList();
		for (ModifierChosen modifierChosen : modifierList) {
			modifierChosen.setFood(null);
			modifierSectionDAO.deleteModifierChosen(modifierChosen);
		}

		// delete FoodOrderItem
		foodOrderItemToDelete.setFood(null);
		foodOrderItemToDelete.setFoodOrder(null);
		foodOrderItemToDelete.setModifierChosenList(null);
		foodOrderItemDAO.updateFoodOrderItem(foodOrderItemToDelete);
		foodOrderItemDAO.deleteFoodOrderItem(foodOrderItemToDelete);
		

	}
	
	public void deleteFoodOrderItemFromFoodOrderTest(int foodOrderItemId) throws Exception {
		FoodOrderItem foodOrderItem = foodOrderItemDAO.getFoodOrderItem(foodOrderItemId);
		FoodOrder foodOrder = foodOrderItem.getFoodOrder();
		//archive FoodOrder if it's the last foodOrderItem to be deleted
		if(foodOrder.getFoodOrderList().size()==1){
			deleteFoodOrderItem(foodOrderItemId);
			foodOrder.setEmployee(null);
			foodOrder.setFoodOrderList(null);
			foodOrder.setOrderPeriod(null);
			foodOrder.setTransactionId(null);
			MyConnection.delete(foodOrder);
		}else{
			deleteFoodOrderItem(foodOrderItemId);
		}
	}

	/**
	 * Returns all FoodOrderItem objects in the OrderPeriod sorted in a HashMap with Employee (key)
	 * and ArrayList<FoodOrderItem>
	 * 
	 * @param orderPeriod OrderPeriod of FoodOrderItem(s) to retrieve
	 * @return a HashMap with Employee as Key and his corresponding FoodOrderItem(s) ordered
	 */
	public HashMap<Employee, ArrayList<FoodOrderItem>> getAllFoodOrderOfOrderPeriod(
			OrderPeriod orderPeriod) {
		// get all orders made today
		List<FoodOrder> tempFoodOrderList = foodOrderDAO.getAllFoodOrderOfOrderPeriod(orderPeriod);
		// hashmap for return later
		HashMap<Employee, ArrayList<FoodOrderItem>> map = new HashMap<Employee, ArrayList<FoodOrderItem>>();

		// iterate through each foodorder from tempfoodorderlist and add it into
		// hashmap. if already have record of existing user, add to that same
		// list
		for (FoodOrder o : tempFoodOrderList) {
			Employee emp = o.getEmployee();

			// check if entry exists. if not, create new arraylist and add into
			// map
			ArrayList<FoodOrderItem> tempItems = map.get(emp);
			if (tempItems == null) {
				tempItems = new ArrayList<FoodOrderItem>();
			}
			tempItems.addAll(o.getFoodOrderList());
			map.put(emp, tempItems);
		}

		Iterator<Employee> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			Employee key = iter.next();
			List<FoodOrderItem> foodOrderItemList = map.get(key);
			ArrayList<FoodOrderItem> foodOrderItemListReturn = new ArrayList<FoodOrderItem>(
					incrementQuantityFoodOrderItem(foodOrderItemList));
			map.put(key, foodOrderItemListReturn);
		}
		return map;
	}

	/**
	 * Returns a ArrayList of FoodDisplayObjects to be use in the admin food order display (by
	 * stalls)
	 * 
	 * @param orderPeriod OrderPeriod of FoodDisplayObject(s) to retrieve
	 * @return a ArrayList of the FoodDisplayObject(s) within the order period
	 */
	public ArrayList<FoodDisplayObject> getAllFoodOrderOfOrderPeriodGroupedByStall(
			OrderPeriod orderPeriod) {

		ArrayList<FoodOrder> tempFoodOrderList = foodOrderDAO
				.getAllFoodOrderOfOrderPeriod(orderPeriod);

		// This is what we will eventually return. (FINAL)
		ArrayList<FoodDisplayObject> foodDisplayList = new ArrayList<FoodDisplayObject>();

		// (A) LinkedHashMap for all the FoodOrderItems with the store as the
		// key
		HashMap<String, ArrayList<FoodOrderItem>> stallToFoodItemLinkedHash = new HashMap<String, ArrayList<FoodOrderItem>>();

		// retrieving all the FoodOrderItems from a FoodOrder in order to
		// populate the
		// (A)stallToFoodItemLinkedHash
		for (int i = 0; i < tempFoodOrderList.size(); i++) {
			FoodOrder tempFoodOrder = tempFoodOrderList.get(i);
			ArrayList<FoodOrderItem> tempFoodOrderItem = new ArrayList<FoodOrderItem>(
					tempFoodOrder.getFoodOrderList());

			// looping through the FoodOrderItems in the tempFoodOrderItemList
			// and then checking
			// if the stall of the FoodOrderItem already exists in
			// (A)stallToFoodItemLinkedHash
			// if it does then add the foodOrderItem into the existing list with
			// the stall as the
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
		Iterator<String> iter = stallToFoodItemLinkedHash.keySet().iterator();

		int count = 1;
		// This will populate the (FINAL) list
		while (iter.hasNext()) {
			// this (B)LinkedHashMap will store all the users who ordered the
			// particular
			// FoodOrderItem.
			HashMap<Integer, ArrayList<Employee>> usernamesForFoodItem = new HashMap<Integer, ArrayList<Employee>>();
			String stallName = (String) iter.next();
			// (AA)This holds all the FoodOrderItems for the stall.
			ArrayList<FoodOrderItem> tempFoodOrderItemForDisplay = stallToFoodItemLinkedHash
					.get(stallName);
			// this (C)LinkedHashMap will store the quantity of the particular
			// FoodOrderItem.
			HashMap<Integer, Integer> quantityForFoodOrderItem = new HashMap<Integer, Integer>();
			ArrayList<FoodOrderItem> uniqueFoodOrderItem = new ArrayList<FoodOrderItem>();
			// this loops Through the foodOrderItems in (AA) in order to take
			// out the unique FoodOrderItems and stores them in
			// UniqueFoodOrderItem
			for (FoodOrderItem i : tempFoodOrderItemForDisplay) {
				Iterator<FoodOrderItem> iterator = uniqueFoodOrderItem.iterator();
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
				int tempquantity = tempItem.getQuantity();
				for (FoodOrderItem i : tempFoodOrderItemForDisplay) {
					if (i.equals2(tempItem)&&i.getFoodOrderItemId()!=tempItem.getFoodOrderItemId()) {
						tempquantity+=i.getQuantity();
					}
				}

				quantityForFoodOrderItem.put(tempItem.getFoodOrderItemId(), tempquantity);
			}

			// not too sure if we can use the old uniqueFoodOrderItemList....
			ArrayList<FoodOrderItem> uniqueFoodOrderItems = new ArrayList<FoodOrderItem>();
			Iterator<Integer> iterFoodItemID = quantityForFoodOrderItem.keySet().iterator();
			FoodOrderItemDAO foodOrderItemDAO = new FoodOrderItemDAO();
			while (iterFoodItemID.hasNext()) {
				int id = (Integer) iterFoodItemID.next();
				FoodOrderItem tempFoodOrderItem = foodOrderItemDAO.getFoodOrderItem(id);
				uniqueFoodOrderItems.add(tempFoodOrderItem);
			}

			// Populating (B) (Users)
			for (FoodOrderItem f : uniqueFoodOrderItems) {
				HashSet<String> usernames = new HashSet<String>();
				HashSet<Employee> employees = new HashSet<Employee>();
				for (FoodOrderItem i : tempFoodOrderItemForDisplay) {
					if (f.equals2(i)) {
						String tempUsername = i.getFoodOrder().getEmployee().getEmail();
						usernames.add(tempUsername);
					}
				}

				for (String s : usernames) {
					Employee tempEmployee = employeeDAO.getEmployeeByEmail(s);
					employees.add(tempEmployee);
				}
				usernamesForFoodItem.put(f.getFoodOrderItemId(),
						new ArrayList<Employee>(employees));
			}
			// Populating the price for the FoodOrderItem
			HashMap<Integer, Double> foodOrderItemPrices = new HashMap<Integer, Double>();
			for (FoodOrderItem f : uniqueFoodOrderItems) {
				double tempPrice = f.getPrice();
				tempPrice = quantityForFoodOrderItem.get(f.getFoodOrderItemId()) * tempPrice;
				foodOrderItemPrices.put(f.getFoodOrderItemId(), tempPrice);
			}

			// Calculating the total price
			Iterator<Integer> totalPriceIterator = foodOrderItemPrices.keySet().iterator();
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

	/**
	 * Retrieve the FoodOrder object based on the specified ID
	 * 
	 * @param foodOrderId The ID for the FoodOrder object to be retrieved.
	 * @return The FoodOrder that has the specified ID
	 */
	public FoodOrder getFoodOrder(int foodOrderId) {
		return foodOrderDAO.getFoodOrder(foodOrderId);
	}

	// Retrieve FoodOrders between earlierDate and laterDate
	/**
	 * Retrieve the FoodOrder(s) between the cut-off time
	 * 
	 * @param earlierDate The start of the cut-off time
	 * @param laterDate The end of the cut-off time
	 * @return An ArrayList of FoodOrder that are within the cut-off timing
	 */
	public List<FoodOrder> getFoodOrderBetweenCutOff(Date earlierDate, Date laterDate) {
		return foodOrderDAO.getFoodOrderByDate(earlierDate, laterDate);
	}

	/**
	 * Retrieves an ArrayList of FoodDisplayObject in the designated cut-off time (grouped the
	 * FoodDisplayObject by Stalls)
	 * 
	 * @param earlierDate The start of the cut-off time
	 * @param laterDate The end of the cut-off time
	 * @return An ArrayList of FoodDisplayObject(s) within the designated cut-off time
	 */
	public ArrayList<FoodDisplayObject> getFoodOrderForCutOff(Date earlierDate, Date laterDate) {

		// retrieve the FoodOrders from yesterday 10am to today 10am
		ArrayList<FoodOrder> tempFoodOrderList = new ArrayList<FoodOrder>(
				getFoodOrderBetweenCutOff(earlierDate, laterDate));

		// This is what we will eventually return. (FINAL)
		ArrayList<FoodDisplayObject> foodDisplayList = new ArrayList<FoodDisplayObject>();

		/*
		 * (A) LinkedHashMap for all the FoodOrderItems with the store as the key
		 */
		HashMap<String, ArrayList<FoodOrderItem>> stallToFoodItemLinkedHash = new HashMap<String, ArrayList<FoodOrderItem>>();

		/*
		 * retrieving all the FoodOrderItems from a FoodOrder in order to populate the
		 * (A)stallToFoodItemLinkedHash
		 */
		for (int i = 0; i < tempFoodOrderList.size(); i++) {
			FoodOrder tempFoodOrder = tempFoodOrderList.get(i);
			ArrayList<FoodOrderItem> tempFoodOrderItem = new ArrayList<FoodOrderItem>(
					tempFoodOrder.getFoodOrderList());

			/*
			 * looping through the FoodOrderItems in the tempFoodOrderItemList and then checking if
			 * the stall of the FoodOrderItem already exists in (A)stallToFoodItemLinkedHash if it
			 * does then add the foodOrderItem into the existing list with the stall as the key in
			 * (A) else create a new Stall Key in (A)
			 */
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
		Iterator<String> iter = stallToFoodItemLinkedHash.keySet().iterator();

		int count = 1;
		// This will populate the (FINAL) list
		while (iter.hasNext()) {
			/*
			 * this (B)LinkedHashMap will store all the users who ordered the particular
			 * FoodOrderItem.
			 */
			HashMap<Integer, ArrayList<Employee>> usernamesForFoodItem = new HashMap<Integer, ArrayList<Employee>>();
			String stallName = (String) iter.next();
			// (AA)This holds all the FoodOrderItems for the stall.
			ArrayList<FoodOrderItem> tempFoodOrderItemForDisplay = stallToFoodItemLinkedHash
					.get(stallName);
			/*
			 * this (C)LinkedHashMap will store the quantity of the particular FoodOrderItem.
			 */
			HashMap<Integer, Integer> quantityForFoodOrderItem = new HashMap<Integer, Integer>();
			ArrayList<FoodOrderItem> uniqueFoodOrderItem = new ArrayList<FoodOrderItem>();
			/*
			 * this loops Through the foodOrderItems in (AA) in order to take out the unique
			 * FoodOrderItems and stores them in UniqueFoodOrderItem
			 */
			for (FoodOrderItem i : tempFoodOrderItemForDisplay) {
				Iterator<FoodOrderItem> iterator = uniqueFoodOrderItem.iterator();
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
			Iterator<Integer> iterFoodItemID = quantityForFoodOrderItem.keySet().iterator();
			while (iterFoodItemID.hasNext()) {
				int id = (Integer) iterFoodItemID.next();
				FoodOrderItem tempFoodOrderItem = foodOrderItemDAO.getFoodOrderItem(id);
				uniqueFoodOrderItems.add(tempFoodOrderItem);
			}

			// Populating (B) (Users)
			for (FoodOrderItem f : uniqueFoodOrderItems) {
				HashSet<String> usernames = new HashSet<String>();
				HashSet<Employee> employees = new HashSet<Employee>();
				for (FoodOrderItem i : tempFoodOrderItemForDisplay) {
					if (f.equals2(i)) {
						// String tempUsername =
						// i.getFoodOrder().getEmployee().getEmail();
					}
					for (String s : usernames) {
						Employee tempEmployee = employeeDAO.getEmployeeByEmail(s);
						employees.add(tempEmployee);
					}
					usernamesForFoodItem.put(f.getFoodOrderItemId(),
							new ArrayList<Employee>(employees));
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
			Iterator<Integer> totalPriceIterator = foodOrderItemPrices.keySet().iterator();
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

	/**
	 * Returns A HashMap with all FoodOrderItems between two dates
	 * 
	 * 
	 * @param earlierDate The start date
	 * @param laterDate The end date
	 * @return a HashMap with StallID as Key and the corresponding FoodOrderItem(s) ordered in a
	 *         ArrayList
	 */
	public HashMap<Integer, ArrayList<FoodOrderItem>> getFoodOrderItemsForStall(Date earlierDate,
			Date laterDate) {
		List<FoodOrder> tempFoodOrderList = foodOrderDAO
				.getFoodOrderByDate(today.minusDays(1).toDate(), today.toDate());

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

	public List<FoodOrder> getCompanyFoodOrderSet(Company company) {
		List<FoodOrder> returnList = incrementQuantity(
				foodOrderDAO.getCompanyFoodOrderSet(company));
		return returnList;
	}

	/**
	 * Retrieves all FoodOrder(s) made by a Employee
	 * 
	 * @param email Email of the Employee
	 * @return A list of FoodOrder(s)
	 */
	public List<FoodOrder> getFoodOrderSet(String email) {
		List<FoodOrder> returnList = incrementQuantity(
				foodOrderDAO.getFoodOrderSet(employeeDAO.getEmployeeByEmail(email)));
		return returnList;
	}

	/**
	 * Retrieves a list of FoodOrders under a specified Employee with a specified status
	 * 
	 * @param email The email of the Employee
	 * @param status The status of the FoodOrder
	 * @return A list of FoodOrders with the specified status user the Employee. If no results
	 *         found, returns empty List.
	 */
	public List<FoodOrder> getUserFoodOrdersByStatus(String email, String status) {
		List<FoodOrder> returnList = new ArrayList<FoodOrder>();
		List<FoodOrder> foodOrderList = foodOrderDAO
				.getFoodOrderSet(employeeDAO.getEmployeeByEmail(email));

		for (FoodOrder f : foodOrderList) {
			if (f.getStatus().equals(status)) {
				returnList.add(f);
			}
		}
		returnList = incrementQuantity(returnList);
		return returnList;
	}

	/**
	 * Removes duplicate FoodOrderItems within a list of FoodOrders and increments the quantity
	 * parameter of duplicated FoodOrderItems
	 * 
	 * @param foodOrderList A List of FoodOrder(s)
	 * @return A cleaned list of FoodOrder(s)
	 */
	public List<FoodOrder> incrementQuantity(List<FoodOrder> foodOrderList) {
		List<FoodOrder> returnList = new ArrayList<FoodOrder>();
		for (FoodOrder f : foodOrderList) {
			ArrayList<FoodOrderItem> foodOrderItemList = new ArrayList<FoodOrderItem>(
					f.getFoodOrderList());
			ArrayList<FoodOrderItem> UniquefoodOrderItemList = new ArrayList<FoodOrderItem>();
			for (FoodOrderItem tempFoodItem : foodOrderItemList) {
				Iterator<FoodOrderItem> iterator = UniquefoodOrderItemList.iterator();
				int equalCount = 0;
				while (iterator.hasNext()) {
					FoodOrderItem tempFoodOrderItem = (FoodOrderItem) iterator.next();
					if (tempFoodOrderItem.equals2(tempFoodItem)) {
						equalCount++;
					}
				}
				if (equalCount == 0) {
					UniquefoodOrderItemList.add(tempFoodItem);
				}
			}
			ArrayList<FoodOrderItem> foodOrderItemsWithQuantity = new ArrayList<FoodOrderItem>();
			for (FoodOrderItem tempItem : UniquefoodOrderItemList) {
				int tempquantity = tempItem.getQuantity();
				for (FoodOrderItem i : foodOrderItemList) {
					if (i.equals2(tempItem)&&i.getFoodOrderItemId()!=tempItem.getFoodOrderItemId()) {
						tempquantity+=i.getQuantity();
					}
				}

				tempItem.setQuantity(tempquantity);
				foodOrderItemsWithQuantity.add(tempItem);

			}
			f.setFoodOrderList(new HashSet<FoodOrderItem>(foodOrderItemsWithQuantity));
			returnList.add(f);
		}

		return returnList;
	}

	/**
	 * Removes duplicates within a list of FoodOrderItems and increments the quantity parameter of
	 * duplicated FoodOrderItems
	 * 
	 * @param list a List of FoodOrderItem(s)
	 * @return A cleaned list of FoodOrderItems with quantity incremented
	 */
	public List<FoodOrderItem> incrementQuantityFoodOrderItem(List<FoodOrderItem> list) {
		ArrayList<FoodOrderItem> foodOrderItemList = new ArrayList<FoodOrderItem>(list);
		ArrayList<FoodOrderItem> UniquefoodOrderItemList = new ArrayList<FoodOrderItem>();
		for (FoodOrderItem tempFoodItem : foodOrderItemList) {
			Iterator<FoodOrderItem> iterator = UniquefoodOrderItemList.iterator();
			int equalCount = 0;
			while (iterator.hasNext()) {
				FoodOrderItem tempFoodOrderItem = (FoodOrderItem) iterator.next();
				if (tempFoodOrderItem.equals2(tempFoodItem)) {
					equalCount++;
				}
			}
			if (equalCount == 0) {
				UniquefoodOrderItemList.add(tempFoodItem);
			}
		}
		ArrayList<FoodOrderItem> foodOrderItemsWithQuantity = new ArrayList<FoodOrderItem>();
		for (FoodOrderItem tempItem : UniquefoodOrderItemList) {
			int tempquantity = tempItem.getQuantity();
			for (FoodOrderItem i : foodOrderItemList) {
				if (i.equals2(tempItem)&&i.getFoodOrderItemId()!=tempItem.getFoodOrderItemId()) {
					tempquantity+=i.getQuantity();
				}
			}

			tempItem.setQuantity(tempquantity);
			foodOrderItemsWithQuantity.add(tempItem);

		}

		return foodOrderItemsWithQuantity;
	}

	/**
	 * Replaces all unavailable Food in the database with the employee's favorite food.(Within the
	 * specified time frame)
	 * 
	 * @param foodUnavailableID The id of the unavailable food
	 * @param earlierDate The start date of the time frame
	 * @param laterDate The end date of the time frame
	 * 
	 */
	public void replaceWithFavoriteFood(int foodUnavailableID, Date earlierDate, Date laterDate) {
		FoodController foodCtrl = new FoodController();
		Food foodUnavailable = foodCtrl.getFood(foodUnavailableID);

		ArrayList<Object> foodOrderItemUnavailableList = new ArrayList<Object>(
				foodOrderItemDAO.getFoodOrderItems(foodUnavailable, earlierDate, laterDate));

		for (Object o : foodOrderItemUnavailableList) {
			FoodOrderItem f = (FoodOrderItem) o;
			f.setFood(f.getFoodOrder().getEmployee().getFavoriteFood());
			foodOrderItemDAO.saveFoodOrderItem(f);
		}
	}

	/**
	 * Update a FoodOrder object in Database
	 * 
	 * @param f The FoodOrder object to be updated to Database
	 */
	public void updateFoodOrder(FoodOrder f) {
		foodOrderDAO.updateFoodOrder(f);
	}

	public void updateFoodOrder(int foodOrderItemId, Food newFood,
			Set<ModifierChosen> newModifierChosenSet, int quantity) throws Exception {
		FoodOrderItem foodOrderItemToEdit = foodOrderItemDAO.getFoodOrderItem(foodOrderItemId);
		FoodOrder foodOrderToEdit = foodOrderItemToEdit.getFoodOrder();
		if (foodOrderToEdit.getStatus() == StringValues.PAID) {
			throw new Exception("The food has already been paid for. Cannot edit Food Order.");
		}
		Employee employee = foodOrderToEdit.getEmployee();

		/*
		 * we need to change the amount owed of the employee so we remove the foodOrder price first
		 */
		double amountOwed = employee.getAmountOwed() - foodOrderToEdit.getFinalPrice();

		// edit the foodOrderItem here
		foodOrderItemToEdit.setFood(newFood);
		foodOrderItemToEdit.setModifierChosenList(newModifierChosenSet);
		foodOrderItemToEdit.setQuantity(quantity);
		foodOrderItemToEdit.setRemarks(StringValues.EDITTED_BY_ADMIN);

		// update the amount owed for the employee
		double newFinalPrice = foodOrderToEdit.getFinalPrice();
		amountOwed += newFinalPrice;
		employee.setAmountOwed(amountOwed);

		// update employee, foodOrderItem and foodOrder
		employeeDAO.updateEmployee(employee);
		foodOrderItemDAO.updateFoodOrderItem(foodOrderItemToEdit);
		updateFoodOrder(foodOrderToEdit);
	}

	/*
	 * Returns a Map with monthYear as the key and the list of FoodOrders that is within the
	 * monthYear example of monthYear format: 2016-12
	 */
	public TreeMap<String, ArrayList<FoodOrder>> getFoodOrderSetByMonthYear(Employee employee) {
		// Employee employee = employeeDAO.getEmployeeByEmail(email);
		String email = employee.getEmail();
		List<Object> list = foodOrderDAO.getUniqueMonthYearInFoodOrderForUser(employee);
		List<FoodOrder> userFoodOrders = getFoodOrderSet(email);
		TreeMap<String, ArrayList<FoodOrder>> yearMonthToFoodOrders = new TreeMap<>(
				Collections.reverseOrder());
		for (Object obj : list) {
			String monthYear = (String) obj;
			ArrayList<FoodOrder> sortList = new ArrayList<>();
			for (FoodOrder order : userFoodOrders) {
				String str = order.getCreateDate().toString();
				if (str.contains(monthYear)) {
					sortList.add(order);
					// System.out.println(str + " added");
				}
				// System.out.println("End of 3rd Layer");
			}
			// System.out.println("Putting " + monthYear + " into Map");
			yearMonthToFoodOrders.put(monthYear, sortList);
		}

		return yearMonthToFoodOrders;
	}

	/*
	 * Returns a Map with monthYear as the key and the list of FoodOrders that is within the
	 * monthYear example of monthYear format: 2016-12
	 */
	public TreeMap<String, ArrayList<FoodOrder>> getCompanyFoodOrderSetByMonthYear(
			String companyCode) {
		// Employee employee = employeeDAO.getEmployeeByEmail(email);
		Company company = companyController.getCompanyByCompanyCode(companyCode);
		List<Object> list = foodOrderDAO.getUniqueMonthYearInFoodOrderForCompany(company);
		List<FoodOrder> userFoodOrders = getCompanyFoodOrderSet(company);
		TreeMap<String, ArrayList<FoodOrder>> yearMonthToFoodOrders = new TreeMap<>(
				Collections.reverseOrder());
		for (Object obj : list) {
			String monthYear = (String) obj;
			ArrayList<FoodOrder> sortList = new ArrayList<>();
			for (FoodOrder order : userFoodOrders) {
				String str = order.getCreateDate().toString();
				if (str.contains(monthYear)) {
					sortList.add(order);
					// System.out.println(str + " added");
				}
				// System.out.println("End of 3rd Layer");
			}
			// System.out.println("Putting " + monthYear + " into Map");
			yearMonthToFoodOrders.put(monthYear, sortList);
		}

		return yearMonthToFoodOrders;
	}

	public TreeMap<String, ArrayList<FoodOrder>> getAllFoodOrdersSetByMonthYear() {
		List<Object> list = foodOrderDAO.getUniqueMonthYearInFoodOrders();
		List<FoodOrder> allFoodOrders = foodOrderDAO.getAllFoodOrders();
		TreeMap<String, ArrayList<FoodOrder>> yearMonthToFoodOrders = new TreeMap<>(
				Collections.reverseOrder());
		for (Object obj : list) {
			String monthYear = (String) obj;
			ArrayList<FoodOrder> sortList = new ArrayList<>();
			for (FoodOrder order : allFoodOrders) {
				String str = order.getCreateDate().toString();
				if (str.contains(monthYear)) {
					sortList.add(order);

				}

			}

			yearMonthToFoodOrders.put(monthYear, sortList);
		}

		return yearMonthToFoodOrders;
	}

	public TreeMap<String, Double> getFoodOrderSetTotalPriceByMonthYear(
			TreeMap<String, ArrayList<FoodOrder>> yearMonthToFoodOrders) {
		Set<String> keyList = yearMonthToFoodOrders.keySet();
		TreeMap<String, Double> yearMonthToFoodOrdersTotalPrice = new TreeMap<>(
				Collections.reverseOrder());
		Iterator<String> iter = keyList.iterator();
		while (iter.hasNext()) {
			String yearMonth = (String) iter.next();
			// System.out.println("**********");
			// System.out.println("MONTH " + yearMonth);
			// System.out.println("**********");
			ArrayList<FoodOrder> tList = yearMonthToFoodOrders.get(yearMonth);
			double sumOfFinalPrices = 0.0;
			for (FoodOrder fo : tList) {
				double price = convertPriceToTwoDecimal(fo.getFinalPrice());
				if (price < 0) {
					price = 0.0;
				}
				sumOfFinalPrices += price;
				// System.out.println(fo.getCreateDate().toString() + "\t Final
				// Price:" + price);
				// System.out.println("=======================");

			}
			System.out.println("Total price for the month: " + sumOfFinalPrices);
			System.out.println();
			yearMonthToFoodOrdersTotalPrice.put(yearMonth, sumOfFinalPrices);
			//
		}
		return yearMonthToFoodOrdersTotalPrice;
	}

	public double convertPriceToTwoDecimal(double finalPrice) {
		return Math.round(finalPrice * 100.0) / 100.0;
	}

	public TreeMap<String, ArrayList<FoodOrder>> getCompanyFoodOrderSetByWeek(String companyCode) {
		Company company = companyController.getCompanyByCompanyCode(companyCode);
		List<String> week = foodOrderDAO.getUniqueWeekInFoodOrderForCompany(company);
		List<FoodOrder> companyFoodOrders = getCompanyFoodOrderSet(company);
		ArrayList<ArrayList<Date>> dateList = dateTransformation(week);

		TreeMap<String, ArrayList<FoodOrder>> weekToFoodOrder = new TreeMap<>(
				Collections.reverseOrder());

		for (int i = 0; i < dateList.size(); i++) {
			ArrayList<Date> dlist = dateList.get(i);
			Date start = dlist.get(0);
			Date end = dlist.get(1);

			System.out.println("====");
			ArrayList<FoodOrder> sortList = new ArrayList<>();
			for (FoodOrder fo : companyFoodOrders) {
				Date fd = fo.getCreateDate();
				if ((fd.after(start)) && (fd.before(end))) {
					sortList.add(fo);
				}
			}
			if (!sortList.isEmpty()) {
				weekToFoodOrder.put(week.get(i), sortList);
			}
		}
		return weekToFoodOrder;
	}

	public TreeMap<String, ArrayList<FoodOrder>> getAllFoodOrdersSetByWeek() {
		// String email = employee.getEmail();
		List<String> week = foodOrderDAO.getUniqueWeekInFoodOrders();
		List<FoodOrder> allFoodOrders = foodOrderDAO.getAllFoodOrders();
		ArrayList<ArrayList<Date>> dateList = dateTransformation(week);

		TreeMap<String, ArrayList<FoodOrder>> weekToFoodOrder = new TreeMap<>(
				Collections.reverseOrder());

		for (int i = 0; i < dateList.size(); i++) {
			ArrayList<Date> dlist = dateList.get(i);
			Date start = dlist.get(0);
			Date end = dlist.get(1);

			System.out.println("====");
			ArrayList<FoodOrder> sortList = new ArrayList<>();
			for (FoodOrder fo : allFoodOrders) {
				Date fd = fo.getCreateDate();
				if ((fd.after(start)) && (fd.before(end))) {
					sortList.add(fo);
				}
			}
			if (!sortList.isEmpty()) {
				weekToFoodOrder.put(week.get(i), sortList);
			}
		}
		return weekToFoodOrder;
	}

	public TreeMap<String, ArrayList<FoodOrder>> getFoodOrderSetByWeek(Employee employee) {
		String email = employee.getEmail();
		List<String> week = foodOrderDAO.getUniqueWeekInFoodOrderForUser(employee);
		List<FoodOrder> userFoodOrders = getFoodOrderSet(email);
		ArrayList<ArrayList<Date>> dateList = dateTransformation(week);

		TreeMap<String, ArrayList<FoodOrder>> weekToFoodOrder = new TreeMap<>(
				Collections.reverseOrder());

		for (int i = 0; i < dateList.size(); i++) {
			ArrayList<Date> dlist = dateList.get(i);
			Date start = dlist.get(0);
			Date end = dlist.get(1);

			System.out.println("====");
			ArrayList<FoodOrder> sortList = new ArrayList<>();
			for (FoodOrder fo : userFoodOrders) {
				Date fd = fo.getCreateDate();
				if ((fd.after(start)) && (fd.before(end))) {
					sortList.add(fo);
				}
			}
			if (!sortList.isEmpty()) {
				weekToFoodOrder.put(week.get(i), sortList);
			}
		}
		return weekToFoodOrder;
	}

	public ArrayList<ArrayList<Date>> dateTransformation(List<String> week) {
		ArrayList<ArrayList<Date>> wl = new ArrayList<>();
		for (Object o : week) {
			String d = (String) o;
			String[] arr = d.split("to");
			ArrayList<Date> dateRange = new ArrayList<>();
			for (int i = 0; i < arr.length; i++) {
				// Date.parse(sp);
				String sp = arr[i];
				if (i == 1) {
					sp = arr[i] + " 23:59:59";
				} else {
					sp = arr[i] + " 00:00:00";
				}

				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				try {
					Date date = formatter.parse(sp);
					System.out.println(date);
					dateRange.add(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
			// System.out.println(d);
			wl.add(dateRange);
			System.out.println("");
		}
		return wl;
	}

	public TreeMap<String, Double> getFoodOrderSetTotalPriceByWeek(
			TreeMap<String, ArrayList<FoodOrder>> weekToFoodOrder) {
		TreeMap<String, Double> weekToTotalPrice = new TreeMap<>(Collections.reverseOrder());

		Set<String> keySet = weekToFoodOrder.keySet();
		Iterator<String> iter = keySet.iterator();
		while (iter.hasNext()) {
			String weeklabel = (String) iter.next();
			System.out.println(weeklabel);
			System.out.println("-------");
			ArrayList<FoodOrder> fList = weekToFoodOrder.get(weeklabel);
			double sum = 0.0;
			for (FoodOrder fo : fList) {
				System.out.println(fo.getCreateDate());
				System.out.println(fo.getFinalPrice());
				double price = convertPriceToTwoDecimal(fo.getFinalPrice());
				if (price < 0) {
					price = 0.0;
				}
				sum += price;
			}
			weekToTotalPrice.put(weeklabel, sum);
		}

		return weekToTotalPrice;
	}

	public TreeMap<String, ArrayList<String>> getYearToMonthListForAll() {
		List<Object> yearList = foodOrderDAO.getUniqueYearInFoodOrders();
		List<Object> monthList = foodOrderDAO.getUniqueMonthYearInFoodOrders();
		TreeMap<String, ArrayList<String>> yearToMonthList = new TreeMap<>(
				Collections.reverseOrder());
		for (Object obj : yearList) {
			String year = (String) obj;
			ArrayList<String> filteredMonthList = new ArrayList<>();
			for (Object o : monthList) {
				String month = (String) o;
				if (month.contains(year)) {
					filteredMonthList.add(month);
				}
			}
			yearToMonthList.put(year, filteredMonthList);

		}
		return yearToMonthList;

	}

	public TreeMap<String, ArrayList<String>> getYearToMonthList(Employee employee) {
		List<Object> yearList = foodOrderDAO.getUniqueYearInFoodOrderForUser(employee);
		List<Object> monthList = foodOrderDAO.getUniqueMonthYearInFoodOrderForUser(employee);
		TreeMap<String, ArrayList<String>> yearToMonthList = new TreeMap<>(
				Collections.reverseOrder());
		for (Object obj : yearList) {
			String year = (String) obj;
			ArrayList<String> filteredMonthList = new ArrayList<>();
			for (Object o : monthList) {
				String month = (String) o;
				if (month.contains(year)) {
					filteredMonthList.add(month);
				}
			}
			yearToMonthList.put(year, filteredMonthList);

		}
		return yearToMonthList;

	}

	public TreeMap<String, ArrayList<String>> getCompanyYearToMonthList(String companyCode) {
		Company company = companyController.getCompanyByCompanyCode(companyCode);
		List<Object> yearList = foodOrderDAO.getUniqueYearInFoodOrderForCompany(company);
		List<Object> monthList = foodOrderDAO.getUniqueMonthYearInFoodOrderForCompany(company);

		for (Object o : monthList) {
			System.out.println(o.toString());
		}
		TreeMap<String, ArrayList<String>> yearToMonthList = new TreeMap<>(
				Collections.reverseOrder());
		for (Object obj : yearList) {
			String year = (String) obj;
			ArrayList<String> filteredMonthList = new ArrayList<>();
			for (Object o : monthList) {
				String month = (String) o;
				if (month.contains(year)) {
					filteredMonthList.add(month);
				}
			}
			yearToMonthList.put(year, filteredMonthList);

		}
		return yearToMonthList;

	}

	public JFreeChart generateMonthlyChart(TreeMap<String, Double> yearMonthToTotalPrice,
			String year) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String series1 = "spending";
		String[] keySet = yearMonthToTotalPrice.keySet()
				.toArray(new String[yearMonthToTotalPrice.size()]);
		Arrays.sort(keySet);
		
		
		
		// java.util.Iterator<String> iter = keySet.iterator();
		for (String yearMonth : keySet) {
			if (yearMonth.contains(year)) {
				double price = yearMonthToTotalPrice.get(yearMonth);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
				try {
					Date d = format.parse(yearMonth);
					format.applyPattern("MMM");
					yearMonth = format.format(d);
					System.out.println(yearMonth);
				} catch (Exception e) {
					e.printStackTrace();
				}
				dataset.addValue(price, series1, yearMonth);
				
			}
		}
		JFreeChart chart = ChartFactory.createLineChart("Monthly Spending For " + year,
				"Month", "Amount Spend($)", dataset);
		CategoryPlot plot = chart.getCategoryPlot();
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		  rangeAxis.setUpperMargin(0.15);
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinePaint(Color.black);
		LineAndShapeRenderer lsr = (LineAndShapeRenderer) chart.getCategoryPlot().getRenderer() ;
		lsr.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		lsr.setBaseItemLabelsVisible(true); 
		return chart;

	}

	public JFreeChart generateWeeklyChart(TreeMap<String, ArrayList<FoodOrder>> weekToFoodOrders,
			String week) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String series1 = "spending";

		TreeMap<String, Double> dateToFoodOrders = dateToTotalPrice(weekToFoodOrders, week);
		
		Set<String> datelabel = dateToFoodOrders.keySet();
		Iterator<String> iter1 = datelabel.iterator();
		while (iter1.hasNext()) {
			String date = (String) iter1.next();
			double price = dateToFoodOrders.get(date);
			System.out.println(date + " " + price);
			dataset.addValue(convertPriceToTwoDecimal(price), series1, date);
		}

		JFreeChart chart = ChartFactory.createLineChart("Weekly Spending from " + weekDurationFormatTransformation(week), "Date",
				"Amount Spend($)", dataset);
		CategoryPlot plot = chart.getCategoryPlot();
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		  rangeAxis.setUpperMargin(0.15);
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinePaint(Color.black);
		LineAndShapeRenderer lsr = (LineAndShapeRenderer) chart.getCategoryPlot().getRenderer() ;
		lsr.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		lsr.setBaseItemLabelsVisible(true); 
		
		
		return chart;
	}
	
	public String weekDurationFormatTransformation(String week) {
		String[] array = week.split("to");	
		String weekstart = array[0];
		String weekend = array[1];
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = format.parse(weekstart);
			Date d2 = format.parse(weekend);
			format.applyPattern("dd-MMM-yyyy");
			weekstart = format.format(d1);
			weekend = format.format(d2);
			//System.out.println(yearMonth);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return weekstart + " to " + weekend;
	}
	
	
	public TreeMap<String, Double> dateToTotalPrice(TreeMap<String, ArrayList<FoodOrder>> weekToFoodOrders,String week) {
		Set<String> keySet = weekToFoodOrders.keySet();
		java.util.Iterator<String> iter = keySet.iterator();
		TreeMap<String, Double> dateToFoodOrders = new TreeMap<>();
		String pattern = "dd-MMM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		while (iter.hasNext()) {
			String weekLabel = (String) iter.next();
			if (weekLabel.equals(week)) {
				ArrayList<FoodOrder> foodOrders = weekToFoodOrders.get(weekLabel);
				FoodOrder initFo = foodOrders.get(0);
				double sum = 0.0;
				Date init = initFo.getCreateDate();
				String currentDate = simpleDateFormat.format(init);
				for (int i = 0; i < foodOrders.size(); i++) {
					FoodOrder fo = foodOrders.get(i);
					double value = fo.getFinalPrice();
					Date date = fo.getCreateDate();

					String newconvertedDate = simpleDateFormat.format(date);
					System.out.println(newconvertedDate);
					System.out.println("CURRENT" + currentDate);
					if (newconvertedDate.equals(currentDate)) {
						// System.out.println(sum + " " + value);
						sum += value;
						System.out.println("TRUE: Put " + currentDate + " and " + sum);
						dateToFoodOrders.put(currentDate, sum);
						currentDate = newconvertedDate;
						// sum = 0.0;
					} else if (!newconvertedDate.equals(currentDate)) {

						System.out.println("FALSE : Put " + newconvertedDate + " and " + value);
						dateToFoodOrders.put(newconvertedDate, value);
						currentDate = newconvertedDate;
						sum = value;
					}

				}

			}
		}
		return dateToFoodOrders;
	}
}
