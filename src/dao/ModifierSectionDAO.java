package dao;

import model.Modifier;
import model.ModifierSection;
import connection.MyConnection;

public class ModifierSectionDAO {

	public ModifierSectionDAO() {

	}

	public void saveModifier(Modifier modifier) {
		MyConnection.save(modifier);
	}

	public void saveModifierSection(ModifierSection modifierSection) {
		MyConnection.save(modifierSection);
	}

	public void updateFoodOrder(ModifierSection modifierSection) {
		MyConnection.update(modifierSection);
	}
}
