package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.fileupload.FileItem;

import com.detectlanguage.errors.APIError;

import model.Canteen;
import model.Food;
import model.ModifierSection;
import model.OrderWindow;
import model.Stall;
import net.aksingh.owmjapis.AbstractWeather.Weather;
import services.ChineseValidation;
import services.CloudinaryUpload;
import dao.FoodDAO;

/**
 * Process the function of managing the Food objects' information
 * 
 * @author SMU Team Eureka
 * 
 */
public class FoodController {
	CloudinaryUpload cloudinaryUpload = new CloudinaryUpload();
	ChineseValidation chineseValidation = new ChineseValidation();
	FoodDAO foodDAO = new FoodDAO();

	/**
	 * Creates a default constructor for FoodController
	 */
	public FoodController() {
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

	public boolean deleteImage(String publicId) throws IOException {
		return cloudinaryUpload.deleteImage(publicId);
	}

	public ArrayList<Food> getAllActiveFoodsUnderStall(Stall stall) {
		return foodDAO.getAllActiveFoodsUnderStall(stall);
	}

	/**
	 * Retrieves a list of Food in the Stall
	 * 
	 * @param stall
	 *            The designated Stall
	 * @return An ArrayList of Food objects that are in the designated Stall
	 */
	public ArrayList<Food> getAllFoodsUnderStall(Stall stall) {
		return foodDAO.getAllFoodsUnderStall(stall);
	}

	/**
	 * Retrieve the Food object based on the specified ID
	 * 
	 * @param id
	 *            The ID used for retrieving the Food
	 * @return The Food that has the specified ID
	 */
	public Food getFood(int id) {
		return foodDAO.getFood(id);
	}

	/**
	 * Get food recommendations for a given weather condition and order window
	 * 
	 * @param foodList
	 *            A ArrayList of Food Objects to choose a recommendation from
	 * @param weather
	 *            A String description of a weather status
	 * @return Food which suits the current weather condition
	 */
	public Food getFoodForWeather(ArrayList<Food> foodList, String weather) {
		ArrayList<Food> choices = new ArrayList<Food>();
		for (Food f : foodList) {
			if (f.getWeatherConditions().equals(weather)) {
				choices.add(f);
			}
		}
		Random rand = new Random();
		int pick = rand.nextInt(choices.size());

		return choices.get(pick);
	}

	/**
	 * Get food recommendation for a given weather condition and order window
	 * 
	 * @param orderWindow
	 *            The current orderWindow
	 * @param weather
	 *            The current weather condition
	 * @return Food which suits the current weather condition
	 */
	public Food getFoodForWeather(Weather weather, OrderWindow orderWindow) {
		int weatherCode = weather.getWeatherCode();
		Canteen currentCanteen = orderWindow.getCanteen();
		Set<Stall> stallSet = currentCanteen.getStallList();
		Iterator<Stall> iter = stallSet.iterator();
		ArrayList<Food> foodList = new ArrayList<Food>();

		while (iter.hasNext()) {
			Stall tempStall = (Stall) iter.next();
			foodList.addAll(tempStall.getFoodList());
		}

		// Thunderstorms
		if (weatherCode <= 299 && weatherCode >= 200) {
			return getFoodForWeather(foodList, "thunderstorm");
			// Rain
		} else if (weatherCode <= 599 && weatherCode >= 300) {
			return getFoodForWeather(foodList, "rain");
			// clear or cloudy
		} else if (weatherCode <= 899 && weatherCode >= 800) {
			return getFoodForWeather(foodList, "clear");
			// hot
		} else if (weatherCode == 904) {
			return getFoodForWeather(foodList, "hot");
		} else {
			Random rand = new Random();
			int pick = rand.nextInt(foodList.size());
			return foodList.get(pick);
		}
	}

	/**
	 * Retrieving the image directory of the Food object based on the specified
	 * ID
	 * 
	 * @param id
	 *            The ID used for retrieving the Food
	 * @return The image directory of the Food object
	 */
	public String getFoodImageDirectory(int id) {
		return foodDAO.getFood(id).getImageDirectory();
	}

	public boolean haveChangesInParameters(Food currentFood, String inputName, String inputChineseName,
			String inputDescription, double inputPrice, String inputWeatherConditions) {
		boolean hasChanges = false;
		String currentName = currentFood.getName();
		String currentChineseName = currentFood.getChineseName();
		String currentDesc = currentFood.getDescription();
		double currentPrice = currentFood.getPrice();
		String currentWeather = currentFood.getWeatherConditions();

		if (!currentName.equals(inputName)) {
			hasChanges = true;
		}

		if (!currentChineseName.equals(inputChineseName)) {
			hasChanges = true;
		}

		if (!currentDesc.equals(inputDescription)) {
			hasChanges = true;
		}

		if (currentPrice != inputPrice) {
			hasChanges = true;
		}

		if (!currentWeather.equals(inputWeatherConditions)) {
			hasChanges = true;
		}

		return hasChanges;

	}

	public String[] imageUpload(byte[] file) throws IOException {
		return cloudinaryUpload.imageUpload(file);

	}

	public String[] replaceImage(String oldPublicId, byte[] file) throws IOException {
		return cloudinaryUpload.replaceImage(oldPublicId, file);

	}

	/**
	 * Adds a new Food object to the Database
	 * 
	 * @param f
	 *            The Food object to be added in
	 */
	public void saveFood(Food f) {
		foodDAO.saveFood(f);
	}

	/**
	 * Updates the designated Food object in the Database
	 * 
	 * @param f
	 *            The Food object to be updated
	 */
	public void updateFood(Food f) {
		foodDAO.updateFood(f);
	}

	public void updateModifierListToFood(Food newFood, Set<ModifierSection> modifierSectionList) {
		foodDAO.updateModifierListToFood(newFood, modifierSectionList);
	}

	public void deleteFood(Food food) {
		foodDAO.deleteFood(food);
	}

	public boolean checkChineseWords(String text) throws APIError {
		return chineseValidation.checkForChineseWords(text);
	}

	public void editFood(List<FileItem> items, int foodId, double price) throws Exception {
		String[] parameters = new String[8];
		String[] output = new String[2];
		Food food = getFood(foodId);
		Iterator<FileItem> iter = items.iterator();
		int index = 0;
		byte[] image = null;
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			if (!item.isFormField()) {
				image = item.get();
				if (image.length != 0) {
					String contentType = item.getContentType();
					System.out.println(contentType);
					if (!contentType.equals("image/jpeg")) {
						throw new Exception("Invalid image format");
					}
				}

				// //
				// parameters[index] = output[2];

				// session.setAttribute("url", url);
				// response.sendRedirect("result.jsp");
			} else {
				if (item.getFieldName().equals("chineseName")) {
					String inputValues = item.getString("UTF-8");
					parameters[index] = inputValues;
				} else {
					String inputValues = item.getString();
					parameters[index] = inputValues;
					System.out.println(item.getFieldName());
					System.out.println(inputValues);
				}
			}
			index++;
		}

		boolean isChinese = checkChineseWords(parameters[2]);
		if (!isChinese) {
			throw new Exception(parameters[2] + " is not a valid chinese word.");
		}
		boolean hasChanges = haveChangesInParameters(food, parameters[1], parameters[2], parameters[3], price,
				parameters[5]);

		if (hasChanges) {
			foodDAO.deleteFood(food);
			if (image.length == 0) {
				Food newFood = new Food(parameters[1], parameters[2], parameters[3], price, food.getImageDirectory(),
						food.getPublicId(), food.getStall());
				if (!food.getModifierList().isEmpty()) {
					updateModifierListToFood(newFood, food.getModifierSectionList());
				} else {
					saveFood(newFood);
				}
			} else {
				output = replaceImage(food.getPublicId(), image);

				Food newFood = new Food(parameters[1], parameters[2], parameters[3], price, output[0], output[1],
						food.getStall());
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
				output = replaceImage(food.getPublicId(), image);
				food.setImageDirectory(output[0]);
				food.setPublicId(output[1]);
				updateFood(food);
			}
		}

	}

}
