package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.Canteen;
import model.Driver;
import model.Employee;
import model.Food;
import model.FoodOrder;
import model.FoodOrderItem;
import model.Stall;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.javadocmd.simplelatlng.LatLng;

import controller.CanteenController;
import controller.FoodOrderController;
import dao.CanteenDAO;
import dao.FoodOrderDAO;
public class DataInputter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		CanteenController canteenController = new CanteenController();
//		ArrayList<Stall> hawkerList = new ArrayList<Stall>();
//		
//		Stall hawker1 = new Stall("admin", "123", "ArnoFood" ,92742509, null, new Date(),null);
//		Food apples = new Food("Apple", "Healthy!", 2.00, hawker1, new Date());
//		ArrayList<Food> foodList = new ArrayList<Food>(); 
//		foodList.add(apples);
//		Set<Food> foodSet = new HashSet();
//		foodSet.add(apples);
//		hawker1.setFoodList(foodSet);
//		Set<Stall> hawkerSet = new HashSet();
//		hawkerSet.add(hawker1);
//		Canteen newCanteen = new Canteen("Chomp Chomp", "20 Kensington Park Rd, Singapore 557269" , new LatLng(1.352083,103.819836), new Date(), hawkerSet);
//		hawker1.setCanteen(newCanteen);
		
	
//		CanteenDAO canteenDao = new CanteenDAO();
//		canteenDao.saveCanteen(newCanteen);
//		System.out.println("New Canteen Success!");
		
		
//		FoodOrder(String status, Employee employee, Driver driver, Set<FoodOrderItem> foodOrderList, Date createDate) 
//		Set<FoodOrderItem> foodOrderSet = new HashSet();
//		FoodOrder foodOrder1 = new FoodOrder("In Progress", null, null, foodOrderSet, new Date());
//		Object o = foodOrder1;
//		FoodOrder fo2 = (FoodOrder) o;
		
//		FoodOrder foodOrder2 = new FoodOrder("Failed", null, null, foodOrderSet, new Date());
//		FoodOrder foodOrder3 = new FoodOrder("Complete", null, null, foodOrderSet, new Date());
//		FoodOrderDAO foodOrderDao = new FoodOrderDAO();
//		foodOrderDao.saveFoodOrder(foodOrder1);
//		foodOrderDao.saveFoodOrder(foodOrder2);
//		foodOrderDao.saveFoodOrder(foodOrder3);
		
		FoodOrderController foodOrderController = new FoodOrderController();
		List<FoodOrder> list = foodOrderController.getFoodOrderToday();
		System.out.println("ListSize =" + list.size());
		
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			FoodOrder tempFoodOrder = (FoodOrder)iter.next();
			System.out.println(tempFoodOrder.getStatus());
		}
	}

}
