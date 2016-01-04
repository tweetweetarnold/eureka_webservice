package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import model.Food;
import model.FoodOrder;
import model.Modifier;
import model.Stall;
import connection.MyConnection;

/**
 * Performs the function of Data Access Object for Food model
 * 
 * 
 */
public class FoodDAO {
	CanteenDAO canteenDAO = new CanteenDAO();
	StallDAO stallDAO = new StallDAO();

	/**
	 * Creates a default constructor for FoodDAO
	 */
	public FoodDAO() {

	}

	/**
	 * Retrieves the Food based on the provided ID
	 * 
	 * @param foodId The ID used for retrieving the Food
	 * @return The Food object that has the provided ID
	 */
	public Food getFood(int foodId) {
		return (Food) MyConnection.get(Food.class, foodId);
	}

	/**
	 * Adds a new Food object to the database
	 * 
	 * @param f The Food object to be added in
	 */
	public void saveFood(Food f) {
		MyConnection.save(f);
	}

	/**
	 * Updates the designated Food object in the database
	 * 
	 * @param f The Food object to be updated
	 */
	public void updateFood(Food f) {
		MyConnection.update(f);
	}

	/**
	 * Removes the designated Food object from the database
	 * 
	 * @param f The Food object to be removed
	 */
	public void deleteFood(Food f) {
		MyConnection.delete(f);
	}

	/**
	 * Retrieves the Food from the FoodList based on the provided Food name
	 * 
	 * @param foodList The list of food
	 * @param foodName The name of the Food
	 * @return The Food object from the FoodList which has the provided food name, otherwise,
	 *         returns null
	 */
	public Food getFoodFromFoodList(Set<Food> foodList, String foodName) {
		for (Food f : foodList) {
			if (f.getName().equals(foodName)) {
				return f;
			}
		}
		return null;
	}

	/**
	 * Retrieves the Food from the Stall and Canteen based on the provided food name
	 * 
	 * @param foodName The name of the Food
	 * @param stallName The name of the Stall
	 * @param canteenName The name of the Canteen
	 * @return The Food object from the Stall and Canteen that has the provided food name, otherwise
	 *         returns null
	 */
	public Food getFoodFromStallAndCanteen(String foodName, String stallName, String canteenName) {
		Stall s = canteenDAO.getStallFromCanteen(canteenName, stallName);
		Set<Food> foodList = s.getFoodList();
		return getFoodFromFoodList(foodList, foodName);
	}

	/**
	 * Adds new Modifier to the designated Food
	 * 
	 * @param m The Modifier to be added
	 * @param f The designated Food to add the Modifier
	 */
	public void addModifierToFood(Modifier m, Food f) {
		Set<Modifier> modifierList = f.getModifierList();

		modifierList.add(m);
		updateFood(f);

	}

	/**
	 * Load the validated content of the Food.csv into the database
	 * 
	 * @param content The list of Food data to be loaded into the database
	 */
	public void loadFoodData(List<String[]> content) {
		Iterator iter = content.iterator();
		iter.next();
		while (iter.hasNext()) {
			String[] row = (String[]) iter.next();
			String foodName = row[0].trim();
			String price = row[1].trim();
			double priceValue = Double.parseDouble(price);
			String description = row[2].trim();

			String stallName = row[3].trim();
			String canteenName = row[4].trim();

			System.out.println(stallName);
			Stall stall = canteenDAO.getStallFromCanteen(canteenName, stallName);
			System.out.println(stall);
			Food newFood = new Food(foodName, description, priceValue, null, stall);

			// adds Food to the Stall's foodList
			stallDAO.addFoodToStall(stall, newFood);
			// adds new Food in DB
			saveFood(newFood);

		}
	}

	/**
	 * Retrieves the Modifier from the Food based on the provided modifier name
	 * 
	 * @param modifierName The name of the Modifier
	 * @param f The designated Food for retrieving the Modifier
	 * @return The Modifier object from the Food that has the provided modifier name, otherwise,
	 *         returns null
	 */
	public Modifier getModifierFromFood(String modifierName, Food f) {
		Set<Modifier> modifierList = f.getModifierList();
		for (Modifier m : modifierList) {
			if (m.getName().equals(modifierName)) {
				return m;
			}
		}
		return null;
	}

	/**
	 * Load the validated content of the Modifier.csv into the database
	 * 
	 * @param content The list of Modifier data to be loaded into the database
	 */
	public void loadModifierData(List<String[]> content) {
		Iterator<String[]> iter = content.iterator();
		iter.next();
		while (iter.hasNext()) {
			String[] row = (String[]) iter.next();
			String modifierName = row[0].trim();
			String description = row[1].trim();
			String price = row[2].trim();
			double priceValue = Double.parseDouble(price);

			String foodName = row[3].trim();
			String stallName = row[4].trim();
			String canteenName = row[5].trim();

			Food food = getFoodFromStallAndCanteen(foodName, stallName, canteenName);
			Modifier newModifier = new Modifier(modifierName, description, priceValue, food);

			addModifierToFood(newModifier, food);
		}
	}
	
	/**
	 * Retrieves a List of Food in the Stall
	 * 
	 * @param stall The designated Stall
	 * @return An ArrayList of Food objects that are in the designated Stall
	 */
	public ArrayList<Food> getAllFoodsUnderStall(Stall stall) {
		ArrayList<Food> returnList = new ArrayList<Food>();

		DetachedCriteria dc = DetachedCriteria.forClass(Food.class);
		dc.add(Restrictions.eq("stall", stall));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((Food) o);
		}
		return returnList;
	}
}
