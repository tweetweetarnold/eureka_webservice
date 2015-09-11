package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.javadocmd.simplelatlng.LatLng;

import controller.CanteenController;
import dao.CanteenDAO;
import entity.Canteen;
import entity.Food;
import entity.Hawker;
import entity.OperationDayHours;
public class DataInput {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CanteenController canteenController = new CanteenController();
		ArrayList<Hawker> hawkerList = new ArrayList<Hawker>();
		
		Hawker hawker1 = new Hawker("admin", "123", "ArnoFood" ,92742509, null, new Date(),null, null);
		Food apples = new Food("Apple", "Healthy!", 2.00, hawker1, new Date());
		ArrayList<Food> foodList = new ArrayList<Food>(); 
		foodList.add(apples);
		Set<Food> foodSet = new HashSet();
		foodSet.add(apples);
		hawker1.setFoodList(foodSet);
		Set<Hawker> hawkerSet = new HashSet();
		hawkerSet.add(hawker1);
		Canteen newCanteen = new Canteen("Chomp Chomp", "20 Kensington Park Rd, Singapore 557269" , new LatLng(1.352083,103.819836), new Date(), hawkerSet);
		hawker1.setCanteen(newCanteen);
		
	
		CanteenDAO canteenDao = new CanteenDAO();
		canteenDao.saveCanteen(newCanteen);
		System.out.println("New Canteen Success!");
				
		
	}

}
