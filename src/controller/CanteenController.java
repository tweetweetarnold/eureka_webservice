package controller;

import java.util.ArrayList;
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
	 * @param canteenId
	 *            The ID that belongs to the Canteen
	 * @return A Canteen object that has the specified ID
	 */
	public Canteen getCanteen(int canteenId) {
		return canteenDAO.getCanteen(canteenId);
	}

	public void editCanteen(String canteenId, String name, String address) throws Exception {
		boolean changesExist = false;

		Canteen canteenToEdit = getCanteen(Integer.parseInt(canteenId));
		if (!name.equals(canteenToEdit.getName()) || name != null ) {
			canteenToEdit.setName(name);
			changesExist = true;
		}
		if (!address.equals(canteenToEdit.getAddress())||address != null ) {
			canteenToEdit.setAddress(address);
			changesExist = true;
		}
		
		if(changesExist){
			canteenDAO.updateCanteen(canteenToEdit);
		}else{
			throw new Exception("No changes were made to the Canteen");
		}
		

	}
	
	public void deleteCanteen(String canteenId) throws Exception{
		StallController stallController = new StallController();
		Canteen canteenToDelete = getCanteen(Integer.parseInt(canteenId));
		Set<Stall> stallsToDelete = canteenToDelete.getStallList();
		for(Stall stall: stallsToDelete){
			stallController.deleteStall(stall);
		}
	}
	
	

}
