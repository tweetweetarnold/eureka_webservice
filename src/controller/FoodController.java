package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import model.Canteen;
import model.Food;
import model.OrderWindow;
import model.Stall;
import net.aksingh.owmjapis.AbstractWeather.Weather;
import services.CloudinaryUpload;
import dao.FoodDAO;

/**
 * Process the function of managing the Food objects' information
 * 
 * @author SMU Team Eureka
 * 
 */
public class FoodController {
	FoodDAO foodDAO = new FoodDAO();
	CloudinaryUpload cloudinaryUpload = new CloudinaryUpload();

	/**
	 * Creates a default constructor for FoodController
	 */
	public FoodController() {

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
	 * @param orderWindow The current orderWindow
	 * @param weather The current weather condition
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
	 * Retrieving the image directory of the Food object based on the specified ID
	 * 
	 * @param id The ID used for retrieving the Food
	 * @return The image directory of the Food object
	 */
	public String getFoodImageDirectory(int id) {
		return foodDAO.getFood(id).getImageDirectory();
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
	
	public String[] imageUpload(byte[] file) throws IOException {
		return cloudinaryUpload.imageUpload(file);
		
	}
	
	public boolean deleteImage(String publicId) throws IOException {
		return cloudinaryUpload.deleteImage(publicId);
	}

}
