package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.json.simple.JSONArray;

import com.detectlanguage.errors.APIError;

import dao.FoodDAO;
import model.Food;
import model.ModifierSection;
import model.Stall;
import services.ChineseValidation;
import services.CloudinaryUpload;

/**
 * Process the function of managing the Food objects' information
 * 
 * @author SMU Team Eureka
 * 
 */
public class FoodController {
	CloudinaryUpload cloudinaryUpload = new CloudinaryUpload();
	FoodDAO foodDAO = new FoodDAO();

	/**
	 * Creates a default constructor for FoodController
	 */
	public FoodController() {
	}

	/**
	 * Adds a new food into the database
	 * @param name the name of the food to add
	 * @param chineseName the chinese name of the food to add UTF-8 chinese characters
	 * @param description 
	 * @param weatherConditions 
	 * @param image the byte image of the food to add 
	 * @param modifierList the modifiers belonging to the food to add
	 * @param price the base price of the food
	 * @param stallId the stall id that the food belongs to
	 * @throws Exception if the chinese name is not valid or if the food name already exists in the stall
	 */
	public void addFood(String name, String chineseName, String description,
			String weatherConditions, byte[] image, JSONArray modifierList, double price,
			int stallId) throws Exception {
		String[] output = new String[2];

		StallController stallCtrl = new StallController();

		if (!checkChineseWords(chineseName)) {
			throw new Exception(chineseName + " is not a valid chinese word.");
		}
		Stall s = stallCtrl.getStall(stallId);

		if (checkFoodExists(name, s)) {
			throw new Exception(name + " already exists in " + s.getName());
		}

		output = imageUpload(image);
		Food f = new Food(name, chineseName, description, price, output[0], output[1], s);
		f.setWeatherConditions(weatherConditions);

		System.out.println("saving food: " + f.toString());
		saveFood(f);

	}

	/**
	 * checks if the chinese text entered is valid chinese characters. 
	 * @param text the Chinese text to verify 
	 * @return true if the text is valid. false if the text is invalid
	 * @throws APIError
	 */
	private boolean checkChineseWords(String text) throws APIError {
		return ChineseValidation.checkForChineseWords(text);
	}

	/**
	 * Check if the food name already exists inside the stall
	 * @param inputFoodName the name of the food to check
	 * @param stall the stall to check
	 * @return true if a food with the same food name already exists. false if the food name is unique
	 */
	public boolean checkFoodExists(String inputFoodName, Stall stall) {
		ArrayList<Food> foodList = getAllActiveFoodsUnderStall(stall);
		if (foodList != null) {
			for (Food f : foodList) {
				if (inputFoodName.equals(f.getName())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * archieves food 
	 * @param food the food to archieve
	 */
	public void deleteFood(Food food) {
		foodDAO.deleteFood(food);
	}

	/**
	 * deletes a food's image from the image database
	 * @param publicId the publicid of the food image to delete from cloudinary
	 * @return true if the food image has been deleted
	 * @throws IOException
	 */
	public boolean deleteImage(String publicId) throws IOException {
		return cloudinaryUpload.deleteImage(publicId);
	}

	/**
	 * Edits a existing food 
	 * @param image the image in bytes to replace the existing image
	 * @param parameters the new parameters to replace the old ones in existing food 
	 * @param food the food to edit
	 * @param stallId the stallID of the food
	 * @throws Exception
	 */
	public void editFood(byte[] image, String[] parameters, Food food, int stallId)
			throws Exception {
		String[] cloudinaryOutput = new String[2];
		/*
		 * cloudinaryOutput[0] stores the image url cloudinaryOutput[1] stores the image publicId
		 */

		double price = 0.0;
		try {

			price = Double.parseDouble(parameters[3]);
		} catch (Exception e) {
			throw new Exception("Invalid input for price");
		}

		if (!parameters[2].isEmpty()) {
			boolean isChinese = checkChineseWords(parameters[2]);
			if (!isChinese) {
				throw new Exception(parameters[2] + " is not a valid chinese word.");
			}
		}

		boolean hasChanges = haveChangesInParameters(food, parameters[1], parameters[2],
				parameters[3], price, parameters[5]);

		if (hasChanges) {
			foodDAO.deleteFood(food);
			if (image.length == 0) {
				Food newFood = new Food(parameters[1], parameters[2], parameters[3], price,
						food.getImageDirectory(), food.getPublicId(), food.getStall());
				if (!food.getModifierList().isEmpty()) {
					updateModifierListToFood(newFood, food.getModifierSectionList());
				} else {
					saveFood(newFood);
				}
			} else {
				cloudinaryOutput = replaceImage(food.getPublicId(), image);

				Food newFood = new Food(parameters[1], parameters[2], parameters[3], price,
						cloudinaryOutput[0], cloudinaryOutput[1], food.getStall());
				newFood.setWeatherConditions(parameters[5]);
				if (!food.getModifierList().isEmpty()) {
					updateModifierListToFood(newFood, food.getModifierSectionList());
				} else {
					saveFood(newFood);
				}
			}
		} else {
			if (image.length == 0) {
				throw new Exception("The details entered are the same as the current food details");
			} else {
				cloudinaryOutput = replaceImage(food.getPublicId(), image);
				food.setImageDirectory(cloudinaryOutput[0]);
				food.setPublicId(cloudinaryOutput[1]);
				updateFood(food);
			}
		}

	}

	/**
	 * Retrieve all food with the status of "active" from a stall
	 * @param stall the stall to retrieve from
	 * @return list of all food with status of "active"
	 */
	public ArrayList<Food> getAllActiveFoodsUnderStall(Stall stall) {
		return foodDAO.getAllActiveFoodsUnderStall(stall);
	}

	/**
	 * Retrieves a list of Food in the Stall
	 * 
	 * @param stall The designated Stall
	 * @return An ArrayList of Food objects that are in the designated Stall
	 */
	public ArrayList<Food> getAllFoodsUnderStall(Stall stall) {
		return foodDAO.getAllFoodsUnderStall(stall);
	}

	/**
	 * Retrieve the Food object based on the specified ID
	 * 
	 * @param id The ID used for retrieving the Food
	 * @return The Food that has the specified ID
	 */
	public Food getFood(int id) {
		return foodDAO.getFood(id);
	}

	/**
	 * Retrieving the image directory of the Food object based on the specified ID
	 * 
	 * @param id The ID used for retrieving the Food
	 * @return The image directory of the Food object
	 */
	public String getFoodImageDirectory(int id) {
		return foodDAO.getFood(id).getImageDirectory();
	}

	public boolean haveChangesInParameters(Food currentFood, String inputName,
			String inputChineseName, String inputDescription, double inputPrice,
			String inputWeatherConditions) {

		if (currentFood.getName().equals(inputName)) {
			if (currentFood.getChineseName().equals(inputChineseName)) {
				if (currentFood.getDescription().equals(inputDescription)) {
					if (currentFood.getPrice() == inputPrice) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * uploads a file to cloudinary
	 * @param file the byte[] to upload 
	 * @return the public id and the url generated by cloudinary
	 * @throws IOException
	 */
	public String[] imageUpload(byte[] file) throws IOException {
		return cloudinaryUpload.imageUpload(file);

	}

	/**
	 * Adds new food to a stall
	 * @param image the image of the food to add
	 * @param parameters the attributes of the new food object
	 * @param stallId the stall to add the food to
	 * @throws Exception
	 */
	public void processAddingFood(byte[] image, String[] parameters, int stallId) throws Exception {
		String[] cloudinaryOutput = new String[2];
		/*
		 * cloudinaryOutput[0] stores the image url cloudinaryOutput[1] stores the image publicId
		 */
		StallController stallCtrl = new StallController();
		double price = Double.parseDouble(parameters[4]);

		Stall stall = stallCtrl.getStall(stallId);
		if (!parameters[2].isEmpty()) {
			boolean isChinese = checkChineseWords(parameters[2]);
			if (!isChinese) {
				throw new Exception(parameters[2] + " is not a valid chinese word.");
			}
		}
		boolean foodExists = checkFoodExists(parameters[1], stall);
		if (foodExists) {
			throw new Exception(parameters[1] + " already exists in " + stall.getName());
		}
		if (image.length == 0) {
			Food food = new Food(parameters[1], parameters[2], parameters[3], price, null, null,
					stall);

			food.setWeatherConditions(parameters[5]);

			System.out.println("foodname: " + food.getName());
			System.out.println("saving food...");
			saveFood(food);
		} else {
			cloudinaryOutput = imageUpload(image);
			Food food = new Food(parameters[1], parameters[2], parameters[3], price,
					cloudinaryOutput[0], cloudinaryOutput[1], stall);

			food.setWeatherConditions(parameters[5]);

			System.out.println("foodname: " + food.getName());
			System.out.println("saving food...");
			saveFood(food);
		}

	}

	/**
	 * this replaces the exiting food image with a new food image
	 * @param oldPublicId the old public id of the food
	 * @param file the new image in byte[] format to upload 
	 * @return the publicId and the url generated from cloudinary
	 * @throws IOException
	 */
	public String[] replaceImage(String oldPublicId, byte[] file) throws IOException {
		return cloudinaryUpload.replaceImage(oldPublicId, file);

	}

	/**
	 * Adds a new Food object to the Database
	 * 
	 * @param f The Food object to be added in
	 */
	public void saveFood(Food f) {
		foodDAO.saveFood(f);
	}

	/**
	 * Updates the designated Food object in the Database
	 * 
	 * @param f The Food object to be updated
	 */
	public void updateFood(Food f) {
		foodDAO.updateFood(f);
	}

	/**
	 * Updates the modifier sections of a Food object
	 * @param newFood the food to update
	 * @param modifierSectionList the modifierSections to put into the food
	 */
	public void updateModifierListToFood(Food newFood, Set<ModifierSection> modifierSectionList) {
		foodDAO.updateModifierListToFood(newFood, modifierSectionList);
	}
}
