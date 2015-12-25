package controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.opencsv.CSVReader;

import dao.CanteenDAO;
import dao.FoodDAO;

import dao.StallDAO;
import model.Canteen;
import model.Food;
import model.Modifier;
import model.Stall;

public class FileUploadController {
	StallDAO stallDAO = new StallDAO();
	CanteenDAO canteenDAO = new CanteenDAO();
	FoodDAO foodDAO = new FoodDAO();
	
	
	public void processStallUpload(InputStream is) throws Exception {
		System.out.println("PROCESS_STALL_FILEUPLOAD");
		List<String[]> content = null;
		BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis);
        BufferedReader br = new BufferedReader(isr);
        //Set<Stall> stallList = new HashSet<Stall>();
        Canteen canteen = null;
//		try {
		        CSVReader csvreader = new CSVReader(br);
				content = csvreader.readAll();
		
				Iterator iter = content.iterator();
	            iter.next();
	            while (iter.hasNext()) {
	                String[] row = (String[]) iter.next();
	                String stallName = row[0].trim();
	                String contactNum = row[1].trim();
	                long contactNumber = Long.parseLong(contactNum);
	                String canteenName = row[2].trim();
	                canteen = canteenDAO.getCanteenByName(canteenName);
	               
	                Stall newStall = new Stall(stallName, contactNumber, canteen, null, null);
	             //   stallList.add(newStall);
	                canteenDAO.addStallToCanteen(canteen, newStall);
	                stallDAO.saveStall(newStall);
	            }
	            
	          //  canteen.setStallList(stallList);
	          //  canteenDAO.updateCanteen(canteen);
	          //  for (Stall stall : stallList) {
	          //  	stallDAO.saveStall(stall);
	         //   }
	            
			
	            csvreader.close();
	     
	     
	     
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public void processCanteenUpload(InputStream is) throws Exception {
		System.out.println("PROCESS_CANTEEN_FILEUPLOAD");
		List<String[]> content = null;
		BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis);
        BufferedReader br = new BufferedReader(isr);
	//	try {
		        CSVReader csvreader = new CSVReader(br);
				content = csvreader.readAll();
		
				Iterator iter = content.iterator();
	            iter.next();
	            while (iter.hasNext()) {
	                String[] row = (String[]) iter.next();
	                String canteenName = row[0].trim();
	                String address = row[1].trim();
	                
					Canteen newCanteen = new Canteen(canteenName, address, null);
					canteenDAO.saveCanteen(newCanteen);
	               // System.out.println(stallName + "<> " + contactNumber);
	            }
			
	            csvreader.close();
	     
	     
	     
	//	} catch (Exception e) {
		//	e.printStackTrace();
	//	}
	}
	
	public void processFoodUpload(InputStream is) throws Exception {
		System.out.println("PROCESS_FOOD_FILEUPLOAD");
		List<String[]> content = null;
		BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis);
        BufferedReader br = new BufferedReader(isr);
        //Set<Stall> stallList = new HashSet<Stall>();
        Canteen canteen = null;
		//try {
		        CSVReader csvreader = new CSVReader(br);
				content = csvreader.readAll();
		
				Iterator iter = content.iterator();
	            iter.next();
	            while (iter.hasNext()) {
	                String[] row = (String[]) iter.next();
	                String foodName = row[0].trim();
	                String price = row[1].trim();
	                double priceValue = Double.parseDouble(price);
	                String description = row[2].trim();
	               
	                String stallName = row[3].trim();
	                String canteenName = row[4].trim();
	                
	                System.out.println(stallName);
	                Stall stall = canteenDAO.getStallFromCanteen(canteenName, stallName);
	                System.out.println(stall);
	                Food newFood = new Food(foodName, description, priceValue, null, stall);
	                
	                //adds Food to the Stall's foodList
	                stallDAO.addFoodToStall(stall, newFood);
	                //adds new Food in DB
	                foodDAO.saveFood(newFood);

	          //  System.out.println(foodName + " " + price + " " + description + " " + stallName + " " + canteenName);
	            }
	            csvreader.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	
	public void processModifierUpload(InputStream is) throws Exception {
		System.out.println("PROCESS_MODIFIER_FILEUPLOAD");
		List<String[]> content = null;
		BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis);
        BufferedReader br = new BufferedReader(isr);
        //Set<Stall> stallList = new HashSet<Stall>();
        Canteen canteen = null;
//		try {
		        CSVReader csvreader = new CSVReader(br);
				content = csvreader.readAll();
		
				Iterator iter = content.iterator();
	            iter.next();
	            while (iter.hasNext()) {
	                String[] row = (String[]) iter.next();
	                String modifierName = row[0].trim();
	                String description = row[1].trim();
	                String price = row[2].trim();
	                double priceValue = Double.parseDouble(price);
	               
	                String foodName = row[3].trim();
	                String stallName = row[4].trim();
	                String canteenName = row[5].trim();
	                
	                Food food = foodDAO.getFoodFromStallAndCanteen(foodName, stallName, canteenName);
	                Modifier newModifier = new Modifier(modifierName, description, priceValue, food);
	                
	                foodDAO.addModifierToFood(newModifier, food);
	            }
	            csvreader.close();
	     
	     
	     
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	
	
	
	

}
