package controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Canteen;
import model.Food;
import model.Modifier;
import model.Stall;

import com.opencsv.CSVReader;

import dao.CanteenDAO;
import dao.FoodDAO;
import dao.StallDAO;

/**
 * Process the functions of reading and validating csv files and load it into database
 * 
 * @author SMU Team Eureka
 * 
 */
public class FileUploadController {
	CanteenDAO canteenDAO = new CanteenDAO();
	List<String[]> content = null;
	ArrayList<String> errorsList = null;
	FoodDAO foodDAO = new FoodDAO();
	StallDAO stallDAO = new StallDAO();

	/**
	 * Creates a default constructor for FileUploadController
	 */
	public FileUploadController() {

	}

	/**
	 * Handles the reading of the Canteen.csv and load it to the database if there are no errors
	 * 
	 * @param is The contents of the Canteen.csv to be read and loaded to database
	 * @return An ArrayList of error(s) if Canteen.csv contains errors, otherwise returns an empty
	 *         ArrayList
	 */
	public ArrayList<String> processCanteenUpload(InputStream is) {
		System.out.println("PROCESS_CANTEEN_FILEUPLOAD");

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
			e.printStackTrace();
		}
		return errorsList;
	}

	/**
	 * Handles the reading of the Food.csv and load it to the database if there are no errors
	 * 
	 * @param is The contents of the Food.csv to be read and loaded to database
	 * @return An ArrayList of error(s) if Food.csv contains errors, otherwise returns an empty
	 *         ArrayList
	 */
	public ArrayList<String> processFoodUpload(InputStream is) {
		System.out.println("PROCESS_FOOD_FILEUPLOAD");

		BufferedInputStream bis = new BufferedInputStream(is);
		InputStreamReader isr = new InputStreamReader(bis);
		BufferedReader br = new BufferedReader(isr);
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
			e.printStackTrace();
		}
		return errorsList;
	}

	/**
	 * Handles the reading of the Modifier.csv and load it to the database if there are no errors
	 * 
	 * @param is The contents of the Modifier.csv to be read and loaded to database
	 * @return An ArrayList of error(s) if Modifier.csv contains errors, otherwise returns an empty
	 *         ArrayList
	 */
	public ArrayList<String> processModifierUpload(InputStream is) {
		System.out.println("PROCESS_MODIFIER_FILEUPLOAD");

		BufferedInputStream bis = new BufferedInputStream(is);
		InputStreamReader isr = new InputStreamReader(bis);
		BufferedReader br = new BufferedReader(isr);
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
			e.printStackTrace();
		}
		return errorsList;
	}

	/**
	 * Handles the reading of the Stall.csv and load it to the database if there are no errors
	 * 
	 * @param is The contents of the Stall.csv to be read and loaded to database
	 * @return An ArrayList of error(s) if Stall.csv contains errors, otherwise returns an empty
	 *         ArrayList
	 */
	public ArrayList<String> processStallUpload(InputStream is) {
		System.out.println("PROCESS_STALL_FILEUPLOAD");

		BufferedInputStream bis = new BufferedInputStream(is);
		InputStreamReader isr = new InputStreamReader(bis);
		BufferedReader br = new BufferedReader(isr);
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
			e.printStackTrace();
		}
		return errorsList;
	}

	/**
	 * Validate the contents of Canteen.csv for any errors before loading to database
	 * 
	 * @param content The List of contents of Canteen.csv to be validated
	 * @return An ArrayList of errors found in the Canteen.csv, otherwise returns an empty ArrayList
	 */
	public ArrayList<String> validateCanteenData(List<String[]> content) {
		ArrayList<String> errorList = new ArrayList<String>();
		Iterator<String[]> iter = content.iterator();
		int rowNumber = 1;
		// Validate the row header
		String[] rowHeader = (String[]) iter.next();
		String canteenNameHeader = rowHeader[0].trim();
		if (canteenNameHeader.isEmpty()) {
			errorList.add("row " + rowNumber
					+ " has a missing row header for Canteen Name in Canteen.csv");
		} else if (!canteenNameHeader.equals("Canteen Name")) {
			errorList.add("row " + rowNumber
					+ " has an invalid row header for Canteen Name in Canteen.csv");
		}

		String addressHeader = rowHeader[1].trim();
		if (addressHeader.isEmpty()) {
			errorList.add("row " + rowNumber
					+ " has a missing row header for Address in Canteen.csv");
		} else if (!addressHeader.equals("Address")) {
			errorList.add("row " + rowNumber
					+ " has an invalid row header for Address in Canteen.csv");
		}

		while (iter.hasNext()) {
			rowNumber++;
			String[] row = (String[]) iter.next();
			String canteenName = row[0].trim();
			if (canteenName.isEmpty()) {
				// errors = true;
				errorList.add("row " + rowNumber + " has empty canteen name in Canteen.csv.");
			}
			Canteen c = canteenDAO.getCanteenByName(canteenName);
			if (c != null) {
				errorList.add("row " + rowNumber + ": This canteen already exists");
			}
			
			String address = row[1].trim();
			if (address.isEmpty()) {
				errorList.add("row " + rowNumber + " has empty address in Canteen.csv.");
			} else {
				Canteen canteen = canteenDAO.getCanteenByAddress(address);
				if (canteen != null) {
					errorList.add("row " + rowNumber + ": This address already exists");
				}
			}

			// check duplicated entries if there is an existing canteen
			
		}
		return errorList;

	}

	/**
	 * Validate the contents of Food.csv for any errors before loading to database
	 * 
	 * @param content The List of contents of Food.csv to be validated
	 * @return An ArrayList of errors found in the Food.csv, otherwise returns an empty ArrayList
	 */
	public ArrayList<String> validateFoodData(List<String[]> content) {
		ArrayList<String> errorList = new ArrayList<String>();
		Iterator<String[]> iter = content.iterator();
		int rowNumber = 1;

		String[] rowHeader = (String[]) iter.next();
		String foodNameHeader = rowHeader[0].trim();
		if (foodNameHeader.isEmpty()) {
			errorList.add("row " + rowNumber
					+ " has a missing row header for Food Name in Food.csv");
		} else if (!foodNameHeader.equals("Food Name")) {
			errorList.add("row " + rowNumber
					+ " has an invalid row header for Food Name in Food.csv");
		}

		String priceHeader = rowHeader[1].trim();
		if (priceHeader.isEmpty()) {
			errorList.add("row " + rowNumber + " has a missing row header for Price in Food.csv");
		} else if (!priceHeader.equals("Price")) {
			errorList.add("row " + rowNumber + " has an invalid row header for Price in Food.csv");
		}

		String descriptionHeader = rowHeader[2].trim();
		if (descriptionHeader.isEmpty()) {
			errorList.add("row " + rowNumber
					+ " has a missing row header for Description in Food.csv");
		} else if (!descriptionHeader.equals("Description")) {
			errorList.add("row " + rowNumber
					+ " has an invalid row header for Description in Food.csv");
		}

		String stallNameHeader = rowHeader[3].trim();
		if (stallNameHeader.isEmpty()) {
			errorList.add("row " + rowNumber
					+ " has a missing row header for Stall Name in Food.csv");
		} else if (!stallNameHeader.equals("Stall Name")) {
			errorList.add("row " + rowNumber
					+ " has an invalid row header for Stall Name in Food.csv");
		}

		String canteenNameHeader = rowHeader[4].trim();
		if (canteenNameHeader.isEmpty()) {
			errorList.add("row " + rowNumber
					+ " has a missing row header for Canteen Name in Food.csv");
		} else if (!canteenNameHeader.equals("Canteen Name")) {
			errorList.add("row " + rowNumber
					+ " has an invalid row header for Canteen Name in Food.csv");
		}

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
			} else if (!price
					.matches("[0-9]|[0-9].[0-9]|[0-9][0-9]|[0-9][0-9].[0-9]|[0-9][0-9].[0-9][0-9]")) {
				errorList.add("row " + rowNumber + " has invalid price value in Food.csv");
			}

			// String description = row[2].trim();

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
				} else if (!stallName.isEmpty()) {

					Stall s = canteenDAO.getStallFromCanteen(canteenName, stallName);
					if (s == null) {
						errorList.add("row " + rowNumber + ": Stall does not exist");
					} else if (!foodName.isEmpty()) {
						Food f = foodDAO.getFoodFromStallAndCanteen(foodName, stallName,
								canteenName);
						if (f != null) {
							errorList.add("row " + rowNumber + ": Food already exists");
						}
					}
				}
			}

		}
		return errorList;
	}

	/**
	 * Validate the contents of Modifier.csv for any errors before loading to database
	 * 
	 * @param content The List of contents of Modifier.csv to be validated
	 * @return An ArrayList of errors found in the Modifier.csv, otherwise returns an empty
	 *         ArrayList
	 */
	public ArrayList<String> validateModifierData(List<String[]> content) {
		ArrayList<String> errorList = new ArrayList<String>();
		Iterator<String[]> iter = content.iterator();
		int rowNumber = 1;

		String[] rowHeader = (String[]) iter.next();
		String modifierNameHeader = rowHeader[0].trim();
		if (modifierNameHeader.isEmpty()) {
			errorList.add("row " + rowNumber
					+ " has a missing row header for Modifier Name in Modifier.csv");
		} else if (!modifierNameHeader.equals("Modifier Name")) {
			errorList.add("row " + rowNumber
					+ " has an invalid row header for Modifier Name in Modifier.csv");
		}

		String descriptionHeader = rowHeader[1].trim();
		if (descriptionHeader.isEmpty()) {
			errorList.add("row " + rowNumber
					+ " has a missing row header for Description in Modifier.csv");
		} else if (!descriptionHeader.equals("Description")) {
			errorList.add("row " + rowNumber
					+ " has an invalid row header for Description in Modifier.csv");
		}

		String priceHeader = rowHeader[2].trim();
		if (priceHeader.isEmpty()) {
			errorList.add("row " + rowNumber
					+ " has a missing row header for Price in Modifier.csv");
		} else if (!priceHeader.equals("Price")) {
			errorList.add("row " + rowNumber
					+ " has an invalid row header for Price in Modifier.csv");
		}
		String foodNameHeader = rowHeader[3].trim();
		if (foodNameHeader.isEmpty()) {
			errorList.add("row " + rowNumber
					+ " has a missing row header for Food Name in Modifier.csv");
		} else if (!foodNameHeader.equals("Food Name")) {
			errorList.add("row " + rowNumber
					+ " has an invalid row header for Food Name in Modifier.csv");
		}

		String stallNameHeader = rowHeader[4].trim();
		if (stallNameHeader.isEmpty()) {
			errorList.add("row " + rowNumber
					+ " has a missing row header for Stall Name in Modifier.csv");
		} else if (!stallNameHeader.equals("Stall Name")) {
			errorList.add("row " + rowNumber
					+ " has an invalid row header for Stall Name in Modifier.csv");
		}

		String canteenNameHeader = rowHeader[5].trim();
		if (canteenNameHeader.isEmpty()) {
			errorList.add("row " + rowNumber
					+ " has a missing row header for Canteen Name in Modifier.csv");
		} else if (!canteenNameHeader.equals("Canteen Name")) {
			errorList.add("row " + rowNumber
					+ " has an invalid row header for Canteen Name in Modifier.csv");
		}

		while (iter.hasNext()) {
			rowNumber++;
			String[] row = (String[]) iter.next();
			String modifierName = row[0].trim();
			if (modifierName.isEmpty()) {
				errorList.add("row " + rowNumber + " has empty modifier name in Modifier.csv");
			}
			// String description = row[1].trim();

			String price = row[2].trim();
			if (price.isEmpty()) {
				errorList.add("row " + rowNumber + " has empty price in Modifier.csv");
			} else if (!price
					.matches("[0-9]|[0-9].[0-9]|[0-9][0-9]|[0-9][0-9].[0-9]|[0-9][0-9].[0-9][0-9]")) {
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
				} else if (!stallName.isEmpty()) {
					Stall s = canteenDAO.getStallFromCanteen(canteenName, stallName);
					if (s == null) {
						errorList.add("row " + rowNumber + ": Stall does not exist");
					} else if (!foodName.isEmpty()) {
						Food f = foodDAO.getFoodFromStallAndCanteen(foodName, stallName,
								canteenName);
						if (f == null) {
							errorList.add("row " + rowNumber + ": Food does not exist");
						} else if (!modifierName.isEmpty()) {
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

	/**
	 * Validate the contents of Stall.csv for any errors before loading to database
	 * 
	 * @param content The List of contents of Stall.csv to be validated
	 * @return An ArrayList of errors found in the Stall.csv, otherwise returns an empty ArrayList
	 */
	public ArrayList<String> validateStallData(List<String[]> content) {
		ArrayList<String> errorList = new ArrayList<String>();
		Canteen canteen = null;
		Iterator<String[]> iter = content.iterator();
		int rowNumber = 1;

		String[] rowHeader = (String[]) iter.next();
		String stallNameHeader = rowHeader[0].trim();
		if (stallNameHeader.isEmpty()) {
			errorList.add("row " + rowNumber
					+ " has a missing row header for Stall Name in Stall.csv");
		} else if (!stallNameHeader.equals("Stall Name")) {
			errorList.add("row " + rowNumber
					+ " has an invalid row header for Stall Name in Stall.csv");
		}

		String contactNumberHeader = rowHeader[1].trim();
		if (contactNumberHeader.isEmpty()) {
			errorList.add("row " + rowNumber
					+ " has a missing row header for Contact Number in Stall.csv");
		} else if (!contactNumberHeader.equals("Contact Number")) {
			errorList.add("row " + rowNumber
					+ " has an invalid row header for Contact Number in Stall.csv");
		}

		String canteenNameHeader = rowHeader[2].trim();
		if (canteenNameHeader.isEmpty()) {
			errorList.add("row " + rowNumber
					+ " has a missing row header for Canteen Name in Stall.csv");
		} else if (!canteenNameHeader.equals("Canteen Name")) {
			errorList.add("row " + rowNumber
					+ " has an invalid row header for Canteen Name in Stall.csv");
		}

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
			} else if (!contactNum.matches("[689][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
				errorList.add("row " + rowNumber + " has invalid contact number in Stall.csv");
			}
			String canteenName = row[2].trim();
			if (canteenName.isEmpty()) {
				errorList.add("row " + rowNumber + " has empty canteen name in Stall.csv");
			} else {
				canteen = canteenDAO.getCanteenByName(canteenName);
				if (canteen == null) {
					errorList.add("row " + rowNumber + ": Canteen does not exist");
				} else if (!stallName.isEmpty()) {
					Stall s = canteenDAO.getStallFromCanteen(canteenName, stallName);
					if (s != null) {
						errorList.add("row " + rowNumber + ":  Stall has already existed");
					}
				}

			}

		}
		return errorList;
	}

}
