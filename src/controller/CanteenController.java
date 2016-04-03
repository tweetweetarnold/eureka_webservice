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

	/**
	 * Creates a default constructor for CanteenController
	 */
	public CanteenController() {
	}

	/**
	 * Creates a new Canteen. Throws Exception if the a canteen of same name already exists.
	 * 
	 * @param name the name of the new Canteen
	 * @param address the address of the new Canteen
	 * 
	 */
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

	/**
	 * Verifies if a canteen of the same name already exists inside the database
	 * 
	 * @param name The name of the new canteen
	 * @param address The address of the new canteen
	 * 
	 * @return returns true if the canteen already exists and false if it does not exist
	 */
	public boolean checkCanteenExists(String name, String address) {
		Canteen c = canteenDAO.getCanteenByName(name);
		if (c != null) {
			return true;
		}
		return false;
	}

	/**
	 * Archieves a existing canteen
	 * 
	 * @param canteenId the canteen id of the canteen to be deleted
	 * 
	 */
	public void deleteCanteen(int canteenId) throws Exception {

		StallController stallCtrl = new StallController();
		Canteen canteenToDelete = getCanteen(canteenId);

		Set<Stall> stallsToDelete = canteenToDelete.getStallList();
		for (Stall stall : stallsToDelete) {
			stallCtrl.deleteStall(stall);
		}
		canteenDAO.deleteCanteen(canteenToDelete);

	}
	

	/**
	 * Edits the attributes of an existing canteen.
	 * 
	 * @param canteenId the canteen id of the canteen to be edited 
	 * @param name the new name of the canteen 
	 * @param address the new address of the canteen
	 */
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
		if (!checkCanteenExists(name, address)) {
			if (changesExist) {
				canteenDAO.updateCanteen(canteenToEdit);
			} else {
				throw new Exception("No changes were made to the Canteen");
			}
		} else {
			throw new Exception("Canteen name already taken");
		}

	}

	/**
	 * retrieves a list of all canteens in the database
	 * 
	 * @return list of all canteens
	 */
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
			errors += "Canteen Address is Empty.";
		}
		return errors;
	}

}
