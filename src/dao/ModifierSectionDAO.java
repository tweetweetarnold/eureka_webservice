package dao;

import model.Modifier;
import model.ModifierChosen;
import model.ModifierSection;
import connection.MyConnection;

public class ModifierSectionDAO {

	public ModifierSectionDAO() {

	}

	public void deleteModifierChosen(ModifierChosen modifierChosen){
		MyConnection.delete(modifierChosen);
	}

	public void saveModifier(Modifier modifier) {
		MyConnection.save(modifier);
	}

	public void saveModifierSection(ModifierSection modifierSection) {
		MyConnection.save(modifierSection);
	}
	
	public void updateModifierSection(ModifierSection modifierSection) {
		MyConnection.update(modifierSection);
	}
}
