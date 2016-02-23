package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.Food;
import model.Modifier;
import model.ModifierSection;
import model.Stall;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import value.StringValues;
import connection.MyConnection;

/**
 * Performs the function of Data Access Object for Food model
 * 
 * @author SMU Team Eureka
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
	 * Archives the designated Food object from the Database
	 * 
	 * @param f The Food object to be removed
	 */
	public void deleteFood(Food f) {
		f.setStatus(StringValues.ARCHIVED);
		// f.setStall(null);
		updateFood(f);
	}

	/**
	 * Retrieves a list of Food in the Stall that are Active
	 * 
	 * @param stall The designated Stall
	 * @return An ArrayList of Food objects that are in the designated Stall
	 */
	public ArrayList<Food> getAllActiveFoodsUnderStall(Stall stall) {
		ArrayList<Food> returnList = new ArrayList<Food>();

		DetachedCriteria dc = DetachedCriteria.forClass(Food.class);
		dc.add(Restrictions.eq("stall", stall));
		dc.add(Restrictions.eq("status", StringValues.ACTIVE));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);
		if (l.size() == 0) {
			return null;
		} else {
			for (Object o : l) {
				returnList.add((Food) o);
			}
			return returnList;
		}
	}
	
	/**
	 * Retrieves a list of Food in the Stall
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
	 * Retrieves the Food from the FoodList based on the provided Food name
	 * 
	 * @param foodList The list of food
	 * @param foodName The name of the Food
	 * @return The Food object from the FoodList which has the provided food name, otherwise,
	 *         returns null
	 */
	public Food getFoodFromFoodList(Set<Food> foodList, String foodName) {
		for (Food f : foodList) {
			if (f.getName().equals(foodName) && f.getStatus().equals(StringValues.ACTIVE)) {
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
			if (m.getName().equals(modifierName) && m.getStatus().equals(StringValues.ACTIVE)) {
				return m;
			}
		}
		return null;
	}

	/**
	 * Load the validated content of the Food.csv into the Database
	 * 
	 * @param content The list of Food data to be loaded into the Database
	 */
	public void loadFoodData(List<String[]> content) {
		Iterator<String[]> iter = content.iterator();
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
			Food newFood = new Food(foodName, null, description, priceValue, null, null, stall);

			// adds Food to the Stall's foodList
			stallDAO.addFoodToStall(stall, newFood);
			// adds new Food in DB
			saveFood(newFood);

		}
	}

	/**
	 * Load the validated content of the Modifier.csv into the Database
	 * 
	 * @param content The list of Modifier data to be loaded into the Database
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
			Modifier newModifier = new Modifier(modifierName, "", description, priceValue, food);

			addModifierToFood(newModifier, food);
		}
	}

	/**
	 * Adds a new Food object to the Database
	 * 
	 * @param f The Food object to be added in
	 */
	public void saveFood(Food f) {
		MyConnection.save(f);
	}

	/**
	 * Updates the designated Food object in the Database
	 * 
	 * @param f The Food object to be updated
	 */
	public void updateFood(Food f) {
		MyConnection.update(f);
	}
	
	public void updateModifierListToFood(Food newFood, Set<ModifierSection> modifierSectionList) {
		Set<Modifier> newList = newFood.getModifierList();
		Set<ModifierSection> newModifierSectionList = new HashSet<ModifierSection>();
		
		newFood.setModifierList(newList);
		for(ModifierSection modSection : modifierSectionList){
			ModifierSection newModifierSection = new ModifierSection();
			Set<Modifier> oldModifierList= modSection.getModifierList();
			Set<Modifier> newModifierList = new HashSet<Modifier>();
			
			for(Modifier m :oldModifierList){
				Modifier newM = new Modifier();
				newM.setCreateDate(new Date());
				newM.setChineseName(m.getChineseName());
				newM.setDescription(m.getDescription());
				newM.setName(m.getName());
				newM.setPrice(m.getPrice());
				newM.setStatus(m.getStatus());
				newM.setFood(newFood);
				newM.setModifierSection(newModifierSection);
				newList.add(newM);
				newModifierList.add(newM);
//				MyConnection.save(newM);
			}
			newModifierSection.setCategoryName(modSection.getCategoryName());
			newModifierSection.setDisplayType(modSection.getDisplayType());
			newModifierSection.setModifierList(newModifierList);
			newModifierSection.setFood(newFood);
			newModifierSectionList.add(newModifierSection);
			ModifierSectionDAO dao = new ModifierSectionDAO();
			//dao.saveModifierSection(newModifierSection);
//			MyConnection.save(newModifierSection);
		}
		newFood.setModifierSectionList(newModifierSectionList);
		newFood.setModifierList(newList);
		saveFood(newFood);
	}
	
	
}
