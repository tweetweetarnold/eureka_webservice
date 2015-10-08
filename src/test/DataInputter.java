package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Canteen;
import model.Food;
import model.FoodOrder;
import model.FoodOrderItem;
import model.Stall;
import dao.FoodOrderDAO;

public class DataInputter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// CanteenController canteenController = new CanteenController();
		// ArrayList<Stall> hawkerList = new ArrayList<Stall>();
		//
		// Stall hawker1 = new Stall("admin", "123", "ArnoFood" ,92742509, null,
		// new Date(),null);
		// Food apples = new Food("Apple", "Healthy!", 2.00, hawker1, new
		// Date());
		// ArrayList<Food> foodList = new ArrayList<Food>();
		// foodList.add(apples);
		// Set<Food> foodSet = new HashSet();
		// foodSet.add(apples);
		// hawker1.setFoodList(foodSet);
		// Set<Stall> hawkerSet = new HashSet();
		// hawkerSet.add(hawker1);
		// Canteen newCanteen = new Canteen("Chomp Chomp", "20 Kensington Park
		// Rd, Singapore 557269" , new LatLng(1.352083,103.819836), new Date(),
		// hawkerSet);
		// hawker1.setCanteen(newCanteen);

		// CanteenDAO canteenDao = new CanteenDAO();
		// canteenDao.saveCanteen(newCanteen);
		// System.out.println("New Canteen Success!");

		// FoodOrder(String status, Employee employee, Driver driver,
		// Set<FoodOrderItem> foodOrderList, Date createDate)
		List<FoodOrderItem> foodOrderSet1 = new ArrayList<>();
		List<FoodOrderItem> foodOrderSet2 = new ArrayList<>();
		List<FoodOrderItem> foodOrderSet3 = new ArrayList<>();
		Canteen canteen = new Canteen("xiaodingdang", "123", new Date(), null);
		Stall stall = new Stall("stall", "123", "foodrepublic", 123, canteen, new Date(), null);
		FoodOrder foodOrder1 = new FoodOrder("In Progress", null, null, foodOrderSet1, new Date());
		Object o = foodOrder1;
		FoodOrder fo2 = (FoodOrder) o;

		FoodOrder foodOrder2 = new FoodOrder("Failed", null, null, foodOrderSet2, new Date());
		FoodOrder foodOrder3 = new FoodOrder("Complete", null, null, foodOrderSet3, new Date());
		Food food = new Food("chickenrice", "damn nice", 2.50, stall, new Date());
		FoodOrderItem foodItem1 = new FoodOrderItem(foodOrder1, food, 2, 2.5, "remarks", new Date());
		FoodOrderItem foodItem2 = new FoodOrderItem(foodOrder1, food, 2, 2.5, "remarks", new Date());
		FoodOrderItem foodItem3 = new FoodOrderItem(foodOrder1, food, 2, 2.5, "remarks", new Date());
		foodOrderSet1.add(foodItem1);
		foodOrderSet1.add(foodItem2);
		foodOrderSet1.add(foodItem3);
		
		
		FoodOrderItem foodItem4 = new FoodOrderItem(foodOrder2, food, 2, 2.5, "remarks", new Date());
		FoodOrderItem foodItem5 = new FoodOrderItem(foodOrder2, food, 2, 2.5, "remarks", new Date());
		FoodOrderItem foodItem6 = new FoodOrderItem(foodOrder2, food, 2, 2.5, "remarks", new Date());
		foodOrderSet2.add(foodItem4);
		foodOrderSet2.add(foodItem5);
		foodOrderSet2.add(foodItem6);
		
		FoodOrderItem foodItem7 = new FoodOrderItem(foodOrder3, food, 2, 2.5, "remarks", new Date());
		FoodOrderItem foodItem8 = new FoodOrderItem(foodOrder3, food, 2, 2.5, "remarks", new Date());
		FoodOrderItem foodItem9 = new FoodOrderItem(foodOrder3, food, 2, 2.5, "remarks", new Date());
		foodOrderSet3.add(foodItem7);
		foodOrderSet3.add(foodItem8);
		foodOrderSet3.add(foodItem9);
		
		FoodOrderDAO foodOrderDao = new FoodOrderDAO();
		foodOrderDao.saveFoodOrder(foodOrder1);
		foodOrderDao.saveFoodOrder(foodOrder2);
		foodOrderDao.saveFoodOrder(foodOrder3);

		// CanteenController controller = new CanteenController();
		// List<Canteen> list = controller.retrieveAll();
		// for(int i = 0; i < list.size(); i++) {
		// Canteen c = list.get(i);
		// System.out.println(c.getAddress());
		// System.out.println(c.getName());
		// Set<Stall> list2 = c.getStallList();
		// Iterator iterator = list2.iterator();
		// Stall s = (Stall) iterator.next();
		// System.out.println("Stall: " + s.getName());
		// }
		//
		// FoodOrderController foodOrderController = new FoodOrderController();
		// List<FoodOrder> list = foodOrderController.getFoodOrderToday();
		// System.out.println("ListSize =" + list.size());
		//
		// Iterator iter = list.iterator();
		// while(iter.hasNext()){
		// FoodOrder tempFoodOrder = (FoodOrder)iter.next();
		// System.out.println(tempFoodOrder.getStatus());
		// }
	}

}
