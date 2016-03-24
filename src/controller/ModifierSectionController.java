package controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.detectlanguage.errors.APIError;

import dao.FoodDAO;
import dao.ModifierSectionDAO;
import model.Food;
import model.Modifier;
import model.ModifierSection;
import services.ChineseValidation;

public class ModifierSectionController {

	public ModifierSectionController() {

	}

	// assuming the person just wants to create an empty container
	public int addModifierSection(String foodID, String categoryName, String displayType) {
		FoodDAO foodDAO = new FoodDAO();
		ModifierSectionDAO modifierSectionDAO = new ModifierSectionDAO();
		Set<Modifier> modifierSectionSet = new HashSet<Modifier>();
		Food food = foodDAO.getFood(Integer.parseInt(foodID));
		// ModifierSection(String categoryName, String displayType, Set<Modifier> modifierList, Food
		// food)
		ModifierSection newModifierSection = new ModifierSection(categoryName, displayType,
				modifierSectionSet, food);

		// Update the food retrieved with the new ModifierSection
		Set<ModifierSection> foodModifierSections = food.getModifierSectionList();
		foodModifierSections.add(newModifierSection);
		food.setModifierSectionList(foodModifierSections);
		// update the existing food object and save the new ModifierSection

		modifierSectionDAO.saveModifierSection(newModifierSection);
		return newModifierSection.getModifierSectionId();
	}

	private boolean checkChineseWords(String text) throws APIError {
		return ChineseValidation.checkForChineseWords(text);
	}

	public boolean createAndAddModifier(String modifierName, String chineseName,
			String modifierDescription, double modifierPrice, String foodID,
			String modifierSectionID) throws Exception {

		boolean modifierSectionExists = false;
		ModifierSection modifierSectionToEdit = null;
		ModifierSectionDAO modifierSectionDAO = new ModifierSectionDAO();
		FoodDAO foodDAO = new FoodDAO();
		Food food = foodDAO.getFood(Integer.parseInt(foodID));

		if (!checkChineseWords(chineseName)) {
			throw new Exception(chineseName + " is not a valid chinese word.");
		}
		Modifier newModifier = new Modifier(modifierName, chineseName, modifierDescription,
				modifierPrice, food);

		// update Modifierlist in food
		Set<Modifier> foodModifierList = food.getModifierList();
		foodModifierList.add(newModifier);
		food.setModifierList(foodModifierList);

		// insert Modifier into an existing ModifierSection
		Set<ModifierSection> modifierSectionList = food.getModifierSectionList();
		Set<ModifierSection> replacementModifierSectionList = new HashSet<ModifierSection>();
		Iterator<ModifierSection> iter = modifierSectionList.iterator();
		while (iter.hasNext()) {
			ModifierSection existingModifierSection = (ModifierSection) iter.next();
			// Check if modifierSection already exists
			if (existingModifierSection.getModifierSectionId() == Integer
					.parseInt(modifierSectionID)) {
				System.out.println("ModifierSection ID = " + modifierSectionID);
				modifierSectionExists = true;
				modifierSectionToEdit = existingModifierSection;
			} else {
				replacementModifierSectionList.add(existingModifierSection);
			}
		}
		if (modifierSectionExists) {
			Set<Modifier> modifierListToEdit = modifierSectionToEdit.getModifierList();
			if (checkDuplicates(modifierListToEdit, newModifier)) {
				throw new Exception("add-ons already exists");
			}
			modifierListToEdit.add(newModifier);
			modifierSectionToEdit.setModifierList(modifierListToEdit);
			replacementModifierSectionList.add(modifierSectionToEdit);
			food.setModifierSectionList(replacementModifierSectionList);
			newModifier.setModifierSection(modifierSectionToEdit);
			modifierSectionDAO.updateModifierSection(modifierSectionToEdit);

			// modifierSectionDAO.saveModifier(newModifier);
			// modifierSectionDAO.saveModifierSection(modifierSectionToEdit);
		}
		return modifierSectionExists;

	}

	public Modifier getModifier(int id) {
		ModifierSectionDAO modifierSectionDAO = new ModifierSectionDAO();
		return modifierSectionDAO.getModifier(id);
	}

	public boolean checkDuplicates(Set<Modifier> modifierList, Modifier m) {
		for (Modifier modifier : modifierList) {
			if (modifier.getName().equals(m.getName())) {
				return true;
			}
		}
		return false;
	}

}
