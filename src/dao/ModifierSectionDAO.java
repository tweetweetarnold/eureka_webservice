package dao;

import connection.MyConnection;
import model.FoodOrder;
import model.Modifier;
import model.ModifierSection;

public class ModifierSectionDAO {
	
	public ModifierSectionDAO(){

	}
	
	public void saveModifierSection(ModifierSection modifierSection){
		MyConnection.save(modifierSection);
	}
	
	public void saveModifier(Modifier modifier){
		MyConnection.save(modifier);
	}
	
	public void updateFoodOrder(ModifierSection modifierSection) {
		MyConnection.update(modifierSection);
	}
}
