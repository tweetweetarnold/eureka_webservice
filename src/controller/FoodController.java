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
	ChineseValidation chineseValidation = new ChineseValidation();
	CloudinaryUpload cloudinaryUpload = new CloudinaryUpload();
	FoodDAO foodDAO = new FoodDAO();

	/**
	 * Creates a default constructor for FoodController
	 */
	public FoodController() {
	}

	// public int addFood(ServletFileUpload upload, HttpServletRequest request) throws Exception {
	// int stallId = 0;
	// int index = 0;
	// String[] parameters = new String[6];
	// String[] output = new String[2];
	// byte[] image = null;
	//
	// // Parse the request
	// List<FileItem> items = upload.parseRequest(request);
	// Iterator<FileItem> iter = items.iterator();
	// while (iter.hasNext()) {
	// FileItem item = (FileItem) iter.next();
	// if (!item.isFormField()) {
	//
	// String contentType = item.getContentType();
	// if (!contentType.equals("image/jpeg")) {
	// throw new Exception("Invalid image format");
	// }
	//
	// image = item.get();
	// } else {
	// if (item.getFieldName().equals("chineseName")) {
	// String inputValues = item.getString("UTF-8");
	// parameters[index] = inputValues;
	// } else {
	// String inputValues = item.getString();
	// parameters[index] = inputValues;
	// System.out.println(item.getFieldName());
	// System.out.println(inputValues);
	// }
	// }
	// index++;
	// }
	//
	// double price = Double.parseDouble(parameters[4]);
	// stallId = Integer.parseInt(parameters[0]);
	//
	// Stall stall = stallController.getStall(stallId);
	// if (!parameters[2].isEmpty()) {
	// boolean isChinese = checkChineseWords(parameters[2]);
	// if (!isChinese) {
	// throw new Exception(parameters[2] + " is not a valid chinese word.");
	// }
	// }
	// boolean foodExists = checkFoodExists(parameters[1], stall);
	// if (foodExists) {
	// throw new Exception(parameters[1] + " already exists in " + stall.getName());
	// }
	//
	// output = imageUpload(image);
	// Food food = new Food(parameters[1], parameters[2], parameters[3], price, output[0],
	// output[1], stall);
	//
	// food.setWeatherConditions(parameters[5]);
	//
	// System.out.println("foodname: " + food.getName());
	// System.out.println("saving food...");
	// saveFood(food);
	// return stallId;
	//
	// }

	public void addFood2(String name, String chineseName, String description,
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

	private boolean checkChineseWords(String text) throws APIError {
		return chineseValidation.checkForChineseWords(text);
	}

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

	public void deleteFood(Food food) {
		foodDAO.deleteFood(food);
	}

	public boolean deleteImage(String publicId) throws IOException {
		return cloudinaryUpload.deleteImage(publicId);
	}

	public void editFood(byte[] image, String[] parameters, Food food, int stallId)
			throws Exception {
		String[] cloudinaryOutput = new String[2];
		/*
		 * cloudinaryOutput[0] stores the image url cloudinaryOutput[1] stores the image publicId
		 */

		double price = 0.0;
		try {
			price = Double.parseDouble(parameters[4]);
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
	 * Get food recommendations for a given weather condition and order window
	 * 
	 * @param foodList A ArrayList of Food Objects to choose a recommendation from
	 * @param weather A String description of a weather status
	 * @return Food which suits the current weather condition
	 */
	// public Food getFoodForWeather(ArrayList<Food> foodList, String weather) {
	// ArrayList<Food> choices = new ArrayList<Food>();
	// for (Food f : foodList) {
	// if (f.getWeatherConditions().equals(weather)) {
	// choices.add(f);
	// }
	// }
	// Random rand = new Random();
	// int pick = rand.nextInt(choices.size());
	//
	// return choices.get(pick);
	// }

	/**
	 * Get food recommendation for a given weather condition and order window
	 * 
	 * @param orderWindow The current orderWindow
	 * @param weather The current weather condition
	 * @return Food which suits the current weather condition
	 */
	// public Food getFoodForWeather(Weather weather, OrderWindow orderWindow) {
	// int weatherCode = weather.getWeatherCode();
	// Canteen currentCanteen = orderWindow.getCanteen();
	// Set<Stall> stallSet = currentCanteen.getStallList();
	// Iterator<Stall> iter = stallSet.iterator();
	// ArrayList<Food> foodList = new ArrayList<Food>();
	//
	// while (iter.hasNext()) {
	// Stall tempStall = (Stall) iter.next();
	// foodList.addAll(tempStall.getFoodList());
	// }
	//
	// // Thunderstorms
	// if (weatherCode <= 299 && weatherCode >= 200) {
	// return getFoodForWeather(foodList, "thunderstorm");
	// // Rain
	// } else if (weatherCode <= 599 && weatherCode >= 300) {
	// return getFoodForWeather(foodList, "rain");
	// // clear or cloudy
	// } else if (weatherCode <= 899 && weatherCode >= 800) {
	// return getFoodForWeather(foodList, "clear");
	// // hot
	// } else if (weatherCode == 904) {
	// return getFoodForWeather(foodList, "hot");
	// } else {
	// Random rand = new Random();
	// int pick = rand.nextInt(foodList.size());
	// return foodList.get(pick);
	// }
	// }

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
						if (currentFood.getWeatherConditions().equals(inputWeatherConditions)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public String[] imageUpload(byte[] file) throws IOException {
		return cloudinaryUpload.imageUpload(file);

	}

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

	public void updateModifierListToFood(Food newFood, Set<ModifierSection> modifierSectionList) {
		foodDAO.updateModifierListToFood(newFood, modifierSectionList);
	}
}
