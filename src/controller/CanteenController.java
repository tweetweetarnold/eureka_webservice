package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Canteen;
import model.Food;
import model.Stall;
import dao.CanteenDAO;

/**
 * Process the business logic of managing the Canteen for the web application
 * 
 * @author SMU Team Eureka
 * 
 */
public class CanteenController {
	CanteenDAO canteenDAO = new CanteenDAO();
	StallController stallCtrl = new StallController();

	/**
	 * Creates a default constructor for CanteenController
	 */
	public CanteenController() {

	}

	public void addCanteen(String name, String address) throws Exception {
		String errorMessages = validateNewCanteenInputs(name, address);
		if (!errorMessages.isEmpty()) {
			throw new Exception(errorMessages);
		}
		boolean canteenExist = checkCanteenExists(name, address);
		if (canteenExist) {
			throw new Exception("Canteen already exists");
		} else {
			Canteen newCanteen = new Canteen(name, address, new HashSet<Stall>());
			canteenDAO.saveCanteen(newCanteen);
		}
		
	}
	
	
	public boolean checkCanteenExists(String name, String address) {
		Canteen c = canteenDAO.getCanteenByName(name);
		Canteen canteenAddress = canteenDAO.getCanteenByAddress(address);
		if (c != null || canteenAddress != null) {
			return true;
		}
		return false;
	}

	public void deleteCanteen(int canteenId) throws Exception {
		CanteenDAO canteenDAO = new CanteenDAO();
		Canteen canteenToDelete = getCanteen(canteenId);

		Set<Stall> stallsToDelete = canteenToDelete.getStallList();
		for (Stall stall : stallsToDelete) {
			stallCtrl.deleteStall(stall);
		}
		canteenDAO.deleteCanteen(canteenToDelete);
		
	}

	public void editCanteen(int canteenId, String name, String address) throws Exception {
		boolean changesExist = false;

		Canteen canteenToEdit = getCanteen(canteenId);
		
		if (!name.equals(canteenToEdit.getName()) || name != null) {
			canteenToEdit.setName(name);
			changesExist = true;
		}
		if (!address.equals(canteenToEdit.getAddress()) || address != null) {
			canteenToEdit.setAddress(address);
			changesExist = true;
		}

		if (changesExist) {
			canteenDAO.updateCanteen(canteenToEdit);
		} else {
			throw new Exception("No changes were made to the Canteen");
		}

	}

	public ArrayList<Canteen> getAllActiveCanteens() {
		return canteenDAO.getAllActiveCanteens();
	}

	/**
	 * Retrieves All the Canteens stored in the Database
	 * 
	 * @return A list of all the Canteen objects from the Database
	 */
	public ArrayList<Canteen> getAllCanteens() {
		return canteenDAO.getAllCanteens();
	}
	
	/**
	 * Retrieves All the Food stored in the Database
	 * 
	 * @return A list of all the Food objects from the Database
	 */
	public List<Food> getAllFood() {
		List<Food> returnList = new ArrayList<Food>();
		List<Canteen> canteenList = getAllCanteens();

		for (Canteen c : canteenList) {
			Set<Stall> stallList = c.getStallList();
			for (Stall s : stallList) {
				Set<Food> foodList = s.getFoodList();
				returnList.addAll(foodList);
			}
		}
		return returnList;
	}
	
	/**
	 * Retrieve a Canteen from the database by an ID
	 * 
	 * @param canteenId The ID that belongs to the Canteen
	 * @return A Canteen object that has the specified ID
	 */
	public Canteen getCanteen(int canteenId) {
		return canteenDAO.getCanteen(canteenId);
	}
	
	private String validateNewCanteenInputs(String name, String address) {
		String errors = "";
		if (name == null || name.isEmpty()) {
			errors += "Canteen Name is Empty.\n";
		}
		
		if (address == null || address.isEmpty()) {
			errors+= "Canteen Address is Empty.";
		}
		return errors;
	}
 
}
