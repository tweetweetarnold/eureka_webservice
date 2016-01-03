package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import model.Canteen;
import model.Food;
import model.OrderWindow;
import model.Stall;
import net.aksingh.owmjapis.AbstractWeather.Weather;
import dao.FoodDAO;

/**
 * Process the function of managing the Food objects' information
 * 
 * 
 */
public class FoodController {
	FoodDAO foodDAO = new FoodDAO();

	/**
	 * Creates a default constructor for FoodController
	 */
	public FoodController() {

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
	 * Retrieve the Food object based on the specified ID
	 * 
	 * @param id The ID used for retrieving the Food
	 * @return The Food that has the specified ID
	 */
	public Food getFood(int id) {
		return foodDAO.getFood(id);
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
		Iterator iter = stallSet.iterator();
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

}
