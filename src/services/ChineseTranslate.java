package services;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import model.FoodDisplayObject;

public class ChineseTranslate {

	private ResourceBundle rb;

	private String resourceName = "RBExample";

	public ArrayList<FoodDisplayObject> translateFoodDisplayObject(ArrayList<FoodDisplayObject> fDO) {
		rb = ResourceBundle.getBundle(resourceName, Locale.forLanguageTag("cn"));
		ArrayList<FoodDisplayObject> tempFDOList = fDO;
		for (FoodDisplayObject f : tempFDOList) {
			f.getPhoneNumber();
		}

		return null;
	}

}
