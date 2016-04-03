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
/**
 * Process the business logic of managing the ModifierSections for the web application
 * 
 * @author SMU Team Eureka
 * 
 */
public class ModifierSectionController {
	/**
	 * Creates a default constructor for ModifierSectionController
	 */
	public ModifierSectionController() {

	}

	// assuming the person just wants to create an empty container
	/**
	 * Adds a new empty ModifierSection to a Food
	 * @param foodID the FoodId to add the ModifierSection to
	 * @param categoryName the new category name of the ModifierSection
	 * @param displayType the type of display that the ModifierSection will allow "d" for dropdown "c" for checkbox
 	 * @return the id of the new ModifierSection
	 */
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
	/**
	 * This creates a new Modifier and adds it to a ModifierSection
	 * @param modifierName the name of the new Modifier
	 * @param chineseName the chinese name of the new Modifier in UTF8 chinese characters
	 * @param modifierDescription 
	 * @param modifierPrice the price of the new Modifier
	 * @param foodID the food that the modifier belongs to
	 * @param modifierSectionID the ModifierSection that the new modifier belongs to
	 * @return true if the modifiersection exists
	 * @throws Exception
	 */
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
	/**
	 * Retrieves a modifier with the specified ID
	 * @param id the id of the modifier to retrieve
	 * @return modifier with the specified ID
	 */
	public Modifier getModifier(int id) {
		ModifierSectionDAO modifierSectionDAO = new ModifierSectionDAO();
		return modifierSectionDAO.getModifier(id);
	}
	/**
	 * checks if a modifier of same name already exists inside a list of modifiers
	 * @param modifierList the list of modifiers to check
	 * @param m the modifier to check for
	 * @return true if the name is used false if it is available
	 */
	public boolean checkDuplicates(Set<Modifier> modifierList, Modifier m) {
		for (Modifier modifier : modifierList) {
			if (modifier.getName().equals(m.getName())) {
				return true;
			}
		}
		return false;
	}

}
