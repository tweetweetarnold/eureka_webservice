package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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
	CloudinaryUpload cloudinaryUpload = new CloudinaryUpload();
	StallDAO stallDAO = new StallDAO();

	/**
	 * Creates a default constructor for StallController
	 */
	public StallController() {
	}

	public boolean checkStallExists(String inputStallName, Canteen c) {
		ArrayList<Stall> stallList = getAllActiveStallsUnderCanteen(c);
		if (stallList != null) {
			for (Stall s : stallList) {
				if (inputStallName.equals(s.getName())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean deleteImage(String publicId) throws IOException {
		return cloudinaryUpload.deleteImage(publicId);
	}

	public void deleteStall(Stall s) {
		FoodController foodController = new FoodController();
		stallDAO.deleteStall(s);
		Set<Food> foodList = s.getFoodList();
		for(Food f :foodList){
			foodController.deleteFood(f);
		}
	}

	public ArrayList<Stall> getAllActiveStallsUnderCanteen(Canteen c) {
		return stallDAO.getAllActiveStallsUnderCanteen(c);
	}

	/**
	 * Retrieves all the Stalls from the Canteen
	 * 
	 * @param canteen
	 *            The designated Canteen to retrieve the Stalls
	 * @return An ArrayList of Stall objects in the designated Canteen
	 */
	public ArrayList<Stall> getAllStallsUnderCanteen(Canteen canteen) {
		return stallDAO.getAllStallsUnderCanteen(canteen);
	}

	/**
	 * Retrieves the Stall based on the provided ID
	 * 
	 * @param stallId
	 *            The ID used for retrieving the Stall
	 * @return The Stall object that has the provided ID
	 */
	public Stall getStall(int stallId) {
		return stallDAO.getStall(stallId);
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

	public String[] imageUpload(byte[] image) throws IOException {
		return cloudinaryUpload.imageUpload(image);
	}

	public void processAddingStall(byte[] image, String[] parameters, int canteenId) throws Exception {
		CanteenController canteenController = new CanteenController();
		String[] cloudinaryOutput = new String[2];
		/*
		 * cloudinaryOutput[0] stores the image url 
		 * cloudinaryOutput[1] stores the image publicId
		 */
		
		long contactNo = validatesContactNumber(parameters[2]);

		Canteen c = canteenController.getCanteen(canteenId);
		boolean stallExists = checkStallExists(parameters[1], c);
		if (stallExists) {
			throw new Exception(parameters[1] + " already exists in " + c.getName());
		}
		cloudinaryOutput = imageUpload(image);

		Stall s = new Stall(parameters[1], contactNo, c, new HashSet<Food>(), cloudinaryOutput[0], cloudinaryOutput[1]);

		System.out.println("stallname: " + s.getName());
		System.out.println("saving food...");
		saveStall(s);

	}

	public void processEditingStall(byte[] image, String[] parameters, Stall stall) throws Exception {
		String[] cloudinaryOutput = new String[2];
		/*
		 * cloudinaryOutput[0] stores the image url 
		 * cloudinaryOutput[1] stores the image publicId
		 */
		long contactNo = validatesContactNumber(parameters[2]);

		boolean hasChanges = haveChangesInParameters(stall, parameters[1], contactNo);
		if (hasChanges) {
			deleteStall(stall);
			if (image.length == 0) {
				Stall newStall = new Stall(parameters[1], contactNo, stall.getCanteen(), new HashSet<Food>(),
						stall.getImageDirectory(), stall.getPublicId());
				if (!stall.getFoodList().isEmpty()) {
					updateFoodListToStall(stall.getFoodList(), newStall);
				}
				saveStall(newStall);
			} else {
				cloudinaryOutput = replaceOldImage(stall.getPublicId(), image);
				Stall newStall = new Stall(parameters[1], contactNo, stall.getCanteen(), new HashSet<Food>(),
						cloudinaryOutput[0], cloudinaryOutput[1]);
				if (!stall.getFoodList().isEmpty()) {
					updateFoodListToStall(stall.getFoodList(), newStall);
				}
				saveStall(newStall);
			}
		} else {
			if (image.length == 0) {
				throw new Exception("The details entered are the same as the current stall details");
			} else {
				cloudinaryOutput = replaceOldImage(stall.getPublicId(), image);
				stall.setImageDirectory(cloudinaryOutput[0]);
				stall.setPublicId(cloudinaryOutput[1]);
				updateStall(stall);
			}
		}
	}

	public String[] replaceOldImage(String oldPublicId, byte[] image) throws IOException {
		return cloudinaryUpload.replaceImage(oldPublicId, image);
	}

	public void saveStall(Stall s) {
		stallDAO.saveStall(s);
	}

	public void updateFoodListToStall(Set<Food> foodList, Stall newStall) {
		stallDAO.setFoodListToStall(foodList, newStall);
	}

	/**
	 * Updates the designated Stall object in the Database
	 * 
	 * @param s
	 *            The Stall object to be updated
	 */
	public void updateStall(Stall s) {
		stallDAO.updateStall(s);
	}

	public long validatesContactNumber(String contactNo) throws Exception {
		if (contactNo.matches("[689][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
			return Long.parseLong(contactNo);
		} else {
			throw new Exception("Invalid contact number");
		}
	}
}
