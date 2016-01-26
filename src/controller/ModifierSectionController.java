package controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import dao.FoodDAO;
import dao.ModifierSectionDAO;
import model.Food;
import model.Modifier;
import model.ModifierSection;
public class ModifierSectionController {
	public ModifierSectionController(){
		
	}
	
	//assuming the person just wants to create an empty container
	public void addModifierSection(String foodID, String categoryName, String displayType){
		FoodDAO foodDAO = new FoodDAO();
		ModifierSectionDAO modifierSectionDAO = new ModifierSectionDAO();
		Set<Modifier> modifierSectionSet = new HashSet<Modifier>();
		Food food = foodDAO.getFood(Integer.parseInt(foodID));
		//ModifierSection(String categoryName, String displayType, Set<Modifier> modifierList, Food food)
		ModifierSection newModifierSection = new ModifierSection(categoryName,displayType,modifierSectionSet,food);
		
		//Update the food retrieved with the new ModifierSection
		Set<ModifierSection> foodModifierSections = food.getModifierSectionList();
		foodModifierSections.add(newModifierSection);
		food.setModifierSectionList(foodModifierSections);
		//update the existing food object and save the new ModifierSection
		foodDAO.updateFood(food);
		modifierSectionDAO.saveModifierSection(newModifierSection);
	}
	
	public boolean createAndAddModifier(String modifierName, String modifierDescription, double modifierPrice, String foodID, String modifierSectionID){
		boolean modifierSectionExists = false;
		ModifierSection modifierSectionToEdit = null;
		ModifierSectionDAO modifierSectionDAO = new ModifierSectionDAO();
		FoodDAO foodDAO = new FoodDAO();
		Food food = foodDAO.getFood(Integer.parseInt(foodID));
		Modifier newModifier = new Modifier(modifierName, modifierDescription, modifierPrice, food);
		
		//update Modifierlist in food
		Set<Modifier> foodModifierList = food.getModifierList();
		foodModifierList.add(newModifier);
		food.setModifierList(foodModifierList);
		
		//insert Modifier into an existing ModifierSection
		Set<ModifierSection> modifierSectionList = food.getModifierSectionList();
		Set<ModifierSection> replacementModifierSectionList = food.getModifierSectionList();
		Iterator iter = modifierSectionList.iterator();
		while(iter.hasNext()){
			ModifierSection existingModifierSection = (ModifierSection)iter.next();
			//Check if modifierSection already exists
			if(existingModifierSection.getModifierSectionId()== Integer.parseInt(modifierSectionID)){
				modifierSectionExists = true;
				modifierSectionToEdit = existingModifierSection;
			}else{
				replacementModifierSectionList.add(existingModifierSection);
			}
		}
		if(modifierSectionExists){
			Set<Modifier> modifierListToEdit = modifierSectionToEdit.getModifierList();
			modifierListToEdit.add(newModifier);
			modifierSectionToEdit.setModifierList(modifierListToEdit);
			replacementModifierSectionList.add(modifierSectionToEdit);
			food.setModifierSectionList(replacementModifierSectionList);
			foodDAO.saveFood(food);
			modifierSectionDAO.saveModifier(newModifier);
			modifierSectionDAO.saveModifierSection(modifierSectionToEdit);
		}
		return modifierSectionExists;
		
	}
	
	
	
}
