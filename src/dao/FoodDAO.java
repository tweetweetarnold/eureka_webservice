package dao;

import model.Food;
import model.Modifier;
import model.Stall;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import connection.MyConnection;

public class FoodDAO {
	CanteenDAO canteenDAO = new CanteenDAO();
	StallDAO stallDAO = new StallDAO();

	// Retrieve Food from the DB with foodID
	public Food getFood(int foodId) {
		return (Food) MyConnection.get(Food.class, foodId);
	}

	// Save new Food in the DB
	public void saveFood(Food f) {
		MyConnection.save(f);
	}

	// Update existing Food in the DB
	public void updateFood(Food f) {
		MyConnection.update(f);
	}

	// Delete Food from the DB
	public void deleteFood(Food f) {
		MyConnection.delete(f);
	}

	public Food getFoodFromFoodList(Set<Food> foodList, String foodName) {
		for (Food f : foodList) {
			if (f.getName().equals(foodName)) {
				return f;
			}
		}
		return null;
	}

	public Food getFoodFromStallAndCanteen(String foodName, String stallName, String canteenName) {
		Stall s = canteenDAO.getStallFromCanteen(canteenName, stallName);
		Set<Food> foodList = s.getFoodList();
		return getFoodFromFoodList(foodList, foodName);

	}

	public void addModifierToFood(Modifier m, Food f) {
		Set<Modifier> modifierList = f.getModifierList();

		modifierList.add(m);
		updateFood(f);

	}

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

	public Modifier getModifierFromFood(String modifierName, Food f) {
		Set<Modifier> modifierList = f.getModifierList();
		for (Modifier m : modifierList) {
			if (m.getName().equals(modifierName)) {
				return m;
			}
		}
		return null;
	}

	public void loadModifierData(List<String[]> content) {
		Iterator iter = content.iterator();
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
}
