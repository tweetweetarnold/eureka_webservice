package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import model.Canteen;
import model.Food;
import model.Stall;
import services.CloudinaryUpload;
import dao.StallDAO;

/**
 * Process the functions of managing the Stall's information
 * 
 * @author SMU Team Eureka
 * 
 */
public class StallController {
	StallDAO stallDAO = new StallDAO();
	CloudinaryUpload cloudinaryUpload = new CloudinaryUpload();
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
	
	public void deleteStall(Stall s) {
		stallDAO.deleteStall(s);
	}
	
	/**
	 * Updates the designated Stall object in the Database
	 * 
	 * @param s The Stall object to be updated
	 */
	public void updateStall(Stall s) {
		stallDAO.updateStall(s);
	}
	
	
	public String[] imageUpload(byte[] image) throws IOException {
		return cloudinaryUpload.imageUpload(image);
	}
	
	public boolean deleteImage(String publicId) throws IOException {
		return cloudinaryUpload.deleteImage(publicId);
	}
	
	public String[] replaceOldImage(String oldPublicId, byte[] image) throws IOException {
		return cloudinaryUpload.replaceImage(oldPublicId, image);
	}
	
	public long validatesContactNumber(String contactNo) throws Exception {
		if (contactNo.matches("[689][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
			return Long.parseLong(contactNo);
		} else {
			throw new Exception("Invalid contact number");
		}
	}
	
	public boolean haveChangesInParameters(Stall oldStall, String inputName, long inputContactNo) {
		boolean hasChanges = false;
		String currentName = oldStall.getName();
		long currentContact = oldStall.getContactNo();
		
		if (!currentName.equals(inputName)) {
			hasChanges = true;
		}
		
		if (currentContact != inputContactNo) {
			hasChanges = true;
		}
		
		return hasChanges;
	}
	
	public ArrayList<Stall> getAllActiveStallsUnderCanteen(Canteen c) {
		return stallDAO.getAllActiveStallsUnderCanteen(c);
	}
	
	public boolean checkStallExists(String inputStallName, Canteen c) {
		ArrayList<Stall> stallList = getAllActiveStallsUnderCanteen(c);
		if (stallList != null) {
			for (Stall s: stallList) {
				if (inputStallName.equals(s.getName())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void updateFoodListToStall(Set<Food> foodList, Stall newStall) {
		stallDAO.setFoodListToStall(foodList, newStall);
	}
}
