package controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	List<String[]> content = null;
	ArrayList<String> errorsList = null;
	
	public ArrayList<String> processStallUpload(InputStream is) throws Exception {
		System.out.println("PROCESS_STALL_FILEUPLOAD");
		
		BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis);
        BufferedReader br = new BufferedReader(isr);
        //Set<Stall> stallList = new HashSet<Stall>();
        Canteen canteen = null;
		try {
		        CSVReader csvreader = new CSVReader(br);
				content = csvreader.readAll();
				csvreader.close(); 
				errorsList = validateStallData(content);
				if (errorsList.size() == 0) {
					stallDAO.loadStallData(content);	
				} else {
					return errorsList;
				}
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return errorsList;
	}
	
	public ArrayList<String> processCanteenUpload(InputStream is)  {
		System.out.println("PROCESS_CANTEEN_FILEUPLOAD");
		//List<String[]> content = null;
		
		BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis);
        BufferedReader br = new BufferedReader(isr);
		try {
	        CSVReader csvreader = new CSVReader(br);
			content = csvreader.readAll();
			csvreader.close(); 
			errorsList = validateCanteenData(content);
			if (errorsList.size() == 0) {
				canteenDAO.loadCanteenData(content);	 
			} else {
				return errorsList;
			} 
		} catch (Exception e) {
			//throw new Exception(e.getMessage());
		}
		return errorsList; 
	}
	
	public ArrayList<String> processFoodUpload(InputStream is) throws Exception {
		System.out.println("PROCESS_FOOD_FILEUPLOAD");
	//	List<String[]> content = null;
		BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis);
        BufferedReader br = new BufferedReader(isr);
        //Set<Stall> stallList = new HashSet<Stall>();
        Canteen canteen = null;
		try {
	        CSVReader csvreader = new CSVReader(br);
			content = csvreader.readAll();
			csvreader.close();
			errorsList = validateFoodData(content);
			if (errorsList.size() == 0) {
				foodDAO.loadFoodData(content);
			} else {
				return errorsList;
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}
	    return errorsList;
	}
	
	
	public ArrayList<String> processModifierUpload(InputStream is) throws Exception {
		System.out.println("PROCESS_MODIFIER_FILEUPLOAD");
		//List<String[]> content = null;
		BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis);
        BufferedReader br = new BufferedReader(isr);
        //Set<Stall> stallList = new HashSet<Stall>();
        Canteen canteen = null;
		try {
	        CSVReader csvreader = new CSVReader(br);
			content = csvreader.readAll();
			csvreader.close();
			errorsList = validateModifierData(content);
			if (errorsList.size() == 0) {
				foodDAO.loadModifierData(content);
			} else {
				return errorsList;
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return errorsList;
	}
	
	public ArrayList<String> validateCanteenData(List<String[]> content) throws Exception {
		ArrayList<String> errorList = new ArrayList<String>();
		Iterator iter = content.iterator();
        iter.next();
        int rowNumber = 1;
        while (iter.hasNext()) {
        	rowNumber++;
            String[] row = (String[]) iter.next();
            String canteenName = row[0].trim();
            if (canteenName.isEmpty()) {
            	//errors = true;
            	errorList.add("row " + rowNumber + " has empty canteen name in Canteen.csv.");
            }
            Canteen c = canteenDAO.getCanteenByName(canteenName);
           
            String address = row[1].trim();
            if (address.isEmpty()) {
            	errorList.add("row " + rowNumber + " has empty address in Canteen.csv.");
            }
            
            //check duplicated entries if there is an existing canteen with same address
            if (c != null) {
            	if (address.equals(c.getAddress())) {
            		errorList.add("row " + rowNumber + ": This canteen already exists");
            	}
            }
        
        }
        return errorList;
		
		
	}
	
	public ArrayList<String> validateStallData(List<String[]> content) throws Exception {
		ArrayList<String> errorList = new ArrayList<String>();
		Canteen canteen = null;
		Iterator iter = content.iterator();
        iter.next();
        int rowNumber = 1;
        while (iter.hasNext()) {
        	rowNumber++;
            String[] row = (String[]) iter.next();
            String stallName = row[0].trim();
            if (stallName.isEmpty()) {
            	errorList.add("row " + rowNumber + " has empty stall name in Stall.csv");
            }
            String contactNum = row[1].trim();
            if (contactNum.isEmpty()) {
            	errorList.add("row " + rowNumber + " has empty contact number in Stall.csv");
            } else if (!contactNum.matches("[0-9]{8}")) {
            	errorList.add("row " + rowNumber + " has invalid contact number in Stall.csv");	
            }
            String canteenName = row[2].trim();
            if (canteenName.isEmpty()) {
            	errorList.add("row " + rowNumber + " has empty canteen name in Stall.csv");
            } else {
            	canteen = canteenDAO.getCanteenByName(canteenName);
            	if (canteen == null) {
            		errorList.add("row " + rowNumber + ": Canteen does not exist");
            	} else if(!stallName.isEmpty()) {
            		Stall s = canteenDAO.getStallFromCanteen(canteenName, stallName);
		        	if (s != null) {
		        		errorList.add("row " + rowNumber + ":  Stall has already existed");
		        	}
            	}
            	
            }
           
        }   
        return errorList;
	}
	
	public ArrayList<String> validateFoodData(List<String[]> content) {
		ArrayList<String> errorList = new ArrayList<String>();
		Iterator iter = content.iterator();
        iter.next();
        int rowNumber = 1;
        while (iter.hasNext()) {
        	rowNumber++;
            String[] row = (String[]) iter.next();
            String foodName = row[0].trim();
            if (foodName.isEmpty()) {
            	errorList.add("row " + rowNumber + " has empty food name in Food.csv");
            }
            String price = row[1].trim();
            if (price.isEmpty()) {
            	errorList.add("row " + rowNumber + " has empty price in Food.csv");
            } else if (!price.matches("[0-9]|[0-9].[0-9]|[0-9][0-9]|[0-9][0-9].[0-9]|[0-9][0-9].[0-9][0-9]")) {
            	errorList.add("row " + rowNumber + " has invalid price value in Food.csv");
            }
           
            String description = row[2].trim();
           
            String stallName = row[3].trim();
            if (stallName.isEmpty()) {
            	errorList.add("row " + rowNumber + " has empty stall name in Food.csv");
            }
            String canteenName = row[4].trim();
            if (canteenName.isEmpty()) {
            	errorList.add("row " + rowNumber + " has empty canteen name in Food.csv");
            } else {
	            Canteen c = canteenDAO.getCanteenByName(canteenName);
	            if (c == null) {
	            	errorList.add("row " + rowNumber + ": Canteen does not exist.");
	            } else if (!stallName.isEmpty()){
	            
	            	Stall s = canteenDAO.getStallFromCanteen(canteenName, stallName);
	            	if (s == null) {
	            		errorList.add("row " + rowNumber + ": Stall does not exist");
	            	} else if (!foodName.isEmpty()){
	            		Food f = foodDAO.getFoodFromStallAndCanteen(foodName, stallName, canteenName);
			            if (f != null) {
			            	errorList.add("row " + rowNumber + ": Food already exists");
			            }
	            	}
	            }
            }
          

        }
        return errorList;
	}
	
	public ArrayList<String> validateModifierData(List<String[]> content) {
		ArrayList<String> errorList = new ArrayList<String>();
		Iterator iter = content.iterator();
        iter.next();
        int rowNumber = 1;
        while (iter.hasNext()) {
        	rowNumber++;
        	String[] row = (String[]) iter.next();
            String modifierName = row[0].trim();
            if (modifierName.isEmpty()) {
            	errorList.add("row " + rowNumber + " has empty modifier name in Modifier.csv");
            }
            String description = row[1].trim();
           
            String price = row[2].trim();
            if (price.isEmpty()) {
            	errorList.add("row " + rowNumber + " has empty price in Modifier.csv");
            } else if (price.matches("[0-9]|[0-9].[0-9]|[0-9][0-9]|[0-9][0-9].[0-9]|[0-9][0-9].[0-9][0-9]")) {
            	errorList.add("row " + rowNumber + " has invalid price value in Modifier.csv");
            }
           
            String foodName = row[3].trim();
            if (foodName.isEmpty()) {
            	errorList.add("row " + rowNumber + " has empty food name in Modifier.csv");
            }
            String stallName = row[4].trim();
            if (stallName.isEmpty()) {
            	errorList.add("row " + rowNumber + " has empty stall name in Modifier.csv");
            }
            
            String canteenName = row[5].trim();
            if (canteenName.isEmpty()) {
            	errorList.add("row " + rowNumber + " has empty canteen name in Modifier.csv");
            } else {
            	Canteen c = canteenDAO.getCanteenByName(canteenName);
	            if (c == null) {
	            	errorList.add("row " + rowNumber + ": Canteen does not exist");
	            } else if (!stallName.isEmpty()){
	            	Stall s = canteenDAO.getStallFromCanteen(canteenName, stallName);
	            	if (s == null) {
	            		errorList.add("row " + rowNumber + ": Stall does not exist");
	            	} else if (!foodName.isEmpty()){
	            		Food f = foodDAO.getFoodFromStallAndCanteen(foodName, stallName, canteenName);
	            		if (f == null) {
	            			errorList.add("row " + rowNumber + ": Food does not exist");
	            		} else if (!modifierName.isEmpty()){
	            			Modifier m = foodDAO.getModifierFromFood(modifierName, f);
	            			if (m != null) {
	            				errorList.add("row " + rowNumber + ": Modifier already exists");
	            					
	            			}
	            		}
	            	}
	            }
            }
        }
        return errorList;
	}
	

}
