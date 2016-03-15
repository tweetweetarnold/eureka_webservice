package dao;

import model.Food;
import model.Modifier;
import model.ModifierChosen;
import model.ModifierSection;
import value.StringValues;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import connection.MyConnection;

public class ModifierSectionDAO {

	public ModifierSectionDAO() {

	}
	
	public ModifierSection getModifierSectionFromFood(String categoryName, String displayType, Food food) {
		//ArrayList<Food> returnList = new ArrayList<Food>();

		DetachedCriteria dc = DetachedCriteria.forClass(ModifierSection.class);
		dc.add(Restrictions.eq("categoryName", categoryName));
		dc.add(Restrictions.eq("displayType", displayType));
		dc.add(Restrictions.eq("food", food));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);
		if (l.size() == 0) {
			return null;
		} else {
			return (ModifierSection) l.get(0);
		}
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
