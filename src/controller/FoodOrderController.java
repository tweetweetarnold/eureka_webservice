package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.FoodOrder;
import model.FoodOrderItem;

import org.joda.time.JodaTimePermission;

import com.google.gson.Gson;

import dao.FoodOrderDAO;

public class FoodOrderController {
	FoodOrderDAO foodOrderDAO = new FoodOrderDAO();

	public FoodOrderController() {

	}

<<<<<<< HEAD
=======
	// Add a new FoodOrder into the DB
>>>>>>> origin/master
	public void addFoodOrder(FoodOrder f) {
		foodOrderDAO.saveFoodOrder(f);
	}

<<<<<<< HEAD
	public FoodOrder getFoodOrder(int foodOrderId) {
		return foodOrderDAO.getFoodOrder(foodOrderId);
	}

	public HashMap getFoodOrderforCutOff() {

=======
	// Retrieve a FoodOrder by id
	public FoodOrder getFoodOrder(int foodOrderId) {
		return foodOrderDAO.getFoodOrder(foodOrderId);
	}
	//Retrieve all FoodOrders from yesterday 10:00:00 to today 10:00:00 with total price as object in HashMap with key "totalPrice"
	public HashMap getFoodOrderforCutOff() {
>>>>>>> origin/master
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
<<<<<<< HEAD
		List<FoodOrder> tempFoodOrderList = foodOrderDAO.getFoodOrderByDateAndTime(dateAfter
				+ " 10:00:00", string + " 10:00:00");
=======
		List<FoodOrder> tempFoodOrderList = foodOrderDAO.getFoodOrderByDateAndTime(dateAfter + " 10:00:00",
				string + " 10:00:00");
>>>>>>> origin/master

		// foodOrderItems With Employee name as key
		HashMap foodOrders = new HashMap();
		Iterator iter = tempFoodOrderList.iterator();
		double totalPrice = 0.0;
		while (iter.hasNext()) {
			FoodOrder tempFoodOrder = (FoodOrder) iter.next();
<<<<<<< HEAD
			ArrayList<FoodOrderItem> foodOrderList = new ArrayList<FoodOrderItem>(
					tempFoodOrder.getFoodOrderList());
=======
			ArrayList<FoodOrderItem> foodOrderList = new ArrayList<FoodOrderItem>(tempFoodOrder.getFoodOrderList());
>>>>>>> origin/master
			foodOrders.put(tempFoodOrder.getEmployee().getUsername(), foodOrderList);
			double price = tempFoodOrder.getFoodOrderTotalPrice();
			totalPrice += price;
		}
		foodOrders.put("totalPrice", totalPrice);
		return foodOrders;
	}

<<<<<<< HEAD
	public HashMap getFoodOrderToday() {

=======
	//Retrieve FoodOrders from today 00:00:00 to today 23:59:59 with total price as object in HashMap with key "totalPrice"
	public HashMap getFoodOrderToday() {
>>>>>>> origin/master
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
<<<<<<< HEAD
		System.out.println("Current date in Date Format: " + string);

		System.out.println(string);
=======
//		System.out.println("Current date in Date Format: " + string);

//		System.out.println(string);
>>>>>>> origin/master
		List<FoodOrder> tempFoodOrderList = foodOrderDAO.getFoodOrderByDate(string);

		// foodOrderItems With Employee name as key
		HashMap foodOrders = new HashMap();
		Iterator iter = tempFoodOrderList.iterator();
		double totalPrice = 0.0;
		while (iter.hasNext()) {
			FoodOrder tempFoodOrder = (FoodOrder) iter.next();
<<<<<<< HEAD
			ArrayList<FoodOrderItem> foodOrderList = new ArrayList<FoodOrderItem>(
					tempFoodOrder.getFoodOrderList());
=======
			ArrayList<FoodOrderItem> foodOrderList = new ArrayList<FoodOrderItem>(tempFoodOrder.getFoodOrderList());
>>>>>>> origin/master
			foodOrders.put(tempFoodOrder.getEmployee().getUsername(), foodOrderList);
			double price = tempFoodOrder.getFoodOrderTotalPrice();
			totalPrice += price;
		}
		foodOrders.put("totalPrice", totalPrice);
		return foodOrders;
	}

}
