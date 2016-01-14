package controller;

import java.util.ArrayList;

import model.Canteen;
import model.Stall;
import dao.StallDAO;

/**
 * Process the functions of managing the Stall's information
 * 
 * @author SMU Team Eureka
 * 
 */
public class StallController {
	StallDAO stallDAO = new StallDAO();

	/**
	 * Creates a default constructor for StallController
	 */
	public StallController() {
	}

	/**
	 * Retrieves all the Stalls from the Canteen
	 * 
	 * @param canteen The designated Canteen to retrieve the Stalls
	 * @return An ArrayList of Stall objects in the designated Canteen
	 */
	public ArrayList<Stall> getAllStallsUnderCanteen(Canteen canteen) {
		return stallDAO.getAllStallsUnderCanteen(canteen);
	}

	/**
	 * Retrieves the Stall based on the provided ID
	 * 
	 * @param stallId The ID used for retrieving the Stall
	 * @return The Stall object that has the provided ID
	 */
	public Stall getStall(int stallId) {
		return stallDAO.getStall(stallId);
	}

	public void saveStall(Stall s) {
		stallDAO.saveStall(s);
	}

	/**
	 * Updates the designated Stall object in the Database
	 * 
	 * @param s The Stall object to be updated
	 */
	public void updateStall(Stall s) {
		stallDAO.updateStall(s);
	}
}
