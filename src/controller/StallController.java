package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import dao.StallDAO;
import model.Canteen;
import model.Food;
import model.Stall;
import services.CloudinaryUpload;

/**
 * Process the functions of managing the Stall's information
 * 
 * @author SMU Team Eureka
 * 
 */
public class StallController {
	CloudinaryUpload cloudinaryUpload = new CloudinaryUpload();
	StallDAO stallDAO = new StallDAO();

	/**
	 * Creates a default constructor for StallController
	 */
	public StallController() {
	}

	public boolean checkStallExists(String name, Canteen c) {

		ArrayList<Stall> stallList = getAllActiveStallsUnderCanteen(c);
		if (stallList != null) {
			for (Stall s : stallList) {
				if (name.equals(s.getName())) {
					return true;
				}
			}
		}
		return false;
	}

	public void deleteActiveFoodInOldStall(Stall s) {
		FoodController foodCtrl = new FoodController();
		Set<Food> foodList = s.getFoodList();
		if (!foodList.isEmpty()) {
			for (Food f : foodList) {
				foodCtrl.deleteFood(f);
			}
		}

	}

	public boolean deleteImage(String publicId) throws IOException {
		return cloudinaryUpload.deleteImage(publicId);
	}

	public void deleteStall(Stall s) {
		stallDAO.deleteStall(s);
	}

	/**
	 * Retrieves the list of active Stalls under this Canteen
	 * 
	 * @param canteen The Canteen whose Stalls are to be retrieved
	 * @return A list of active Stalls under this Canteen
	 */
	public ArrayList<Stall> getAllActiveStallsUnderCanteen(Canteen canteen) {
		return stallDAO.getAllActiveStallsUnderCanteen(canteen);
	}

	/**
	 * Retrieves all the Stalls from the Canteen including those deleted
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

	/**
	 * Check if the specified Stall's name and contact number is different from the specified name
	 * and conatactNo
	 * 
	 * @param stall The Stall whose name and contactNo is to be checked
	 * @param name The name to be used to check against the specified Stall
	 * @param contactNo The contact number to be used to check against the specified Stall
	 * @return The Stall object that has the provided ID
	 */
	private boolean haveChangesInParameters(Stall stall, String name, long contactNo) {
		if (stall.getName().equals(name) && stall.getContactNo() == contactNo) {
			return false;
		}
		return true;
	}

	/**
	 * Uploads an image to Cloudinary to be used for a Stall later
	 * 
	 * @param image The image to be uploaded for this Stall in byte[] format
	 * @return A String array
	 */
	public String[] imageUploadForStall(byte[] image) throws IOException {
		return cloudinaryUpload.stallImageUpload(image);
	}

	public void processAddingStall(byte[] image, String[] parameters, int canteenId)
			throws Exception {

		String[] cloudinaryOutput = new String[2];

		long contactNo = validatesContactNumber(parameters[2]);

		CanteenController canteenCtrl = new CanteenController();
		Canteen c = canteenCtrl.getCanteen(canteenId);
		boolean stallExists = checkStallExists(parameters[1], c);
		if (stallExists) {
			throw new Exception(parameters[1] + " already exists in " + c.getName());
		}
		if (image.length == 0) {
			Stall s = new Stall(parameters[1], contactNo, c, new HashSet<Food>(), null, null);

			System.out.println("stallname: " + s.getName());
			System.out.println("saving food...");
			saveStall(s);
		} else {
			cloudinaryOutput = imageUploadForStall(image);

			Stall s = new Stall(parameters[1], contactNo, c, new HashSet<Food>(),
					cloudinaryOutput[0], cloudinaryOutput[1]);

			System.out.println("stallname: " + s.getName());
			System.out.println("saving food...");
			saveStall(s);
		}

	}

	public void processEditingStall(byte[] image, String[] parameters, Stall stall)
			throws Exception {
		String[] cloudinaryOutput = new String[2];
		long contactNo = validatesContactNumber(parameters[2]);

		boolean hasChanges = haveChangesInParameters(stall, parameters[1], contactNo);
		if (hasChanges) {
			deleteStall(stall);
			if (image.length == 0) {
				Stall newStall = new Stall(parameters[1], contactNo, stall.getCanteen(),
						new HashSet<Food>(), stall.getImageDirectory(), stall.getPublicId());
				System.out.println("The size is: " + stall.getActiveFoodList().size());
				if (!stall.getActiveFoodList().isEmpty()) {
					updateFoodListToStall(stall.getActiveFoodList(), newStall);
				} else {
					saveStall(newStall);
				}
			} else {
				cloudinaryOutput = replaceStallOldImage(stall.getPublicId(), image);
				Stall newStall = new Stall(parameters[1], contactNo, stall.getCanteen(),
						new HashSet<Food>(), cloudinaryOutput[0], cloudinaryOutput[1]);
				if (!stall.getActiveFoodList().isEmpty()) {
					updateFoodListToStall(stall.getActiveFoodList(), newStall);
				} else {
					saveStall(newStall);
				}
			}

		} else {
			if (image.length == 0) {
				throw new Exception(
						"The details entered are the same as the current stall details");
			} else {
				cloudinaryOutput = replaceStallOldImage(stall.getPublicId(), image);
				stall.setImageDirectory(cloudinaryOutput[0]);
				stall.setPublicId(cloudinaryOutput[1]);
				updateStall(stall);
			}
		}
	}

	public String[] replaceStallOldImage(String oldPublicId, byte[] image) throws IOException {
		return cloudinaryUpload.replaceStallImage(oldPublicId, image);
	}

	/**
	 * Adds a new Stall object to the Database
	 * 
	 * @param s The Stall object to be added in
	 */
	public void saveStall(Stall s) {
		stallDAO.saveStall(s);
	}

	/**
	 * Sets a list of Food to the specified Stall
	 * 
	 * @param foodList The list of Food to be updated to the specified Stall
	 * @param newStall The Stall in which the list of Food is to be updated to
	 */
	public void updateFoodListToStall(Set<Food> foodList, Stall newStall) {
		stallDAO.setFoodListToStall(foodList, newStall);
	}

	/**
	 * Updates the designated Stall object in the Database
	 * 
	 * @param s The Stall object to be updated
	 */
	public void updateStall(Stall s) {
		stallDAO.updateStall(s);
	}

	/**
	 * Validates if the specified String is a valid contact number
	 * 
	 * @param contactNo The contact number to be verified
	 */
	private long validatesContactNumber(String contactNo) throws Exception {
		if (contactNo.matches("[689][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
			return Long.parseLong(contactNo);
		} else {
			throw new Exception("Invalid contact number");
		}
	}
}
