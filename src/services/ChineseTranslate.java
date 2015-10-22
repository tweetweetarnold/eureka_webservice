package services;
import java.util.ArrayList;
import java.util.Locale;

import model.FoodDisplayObject;
import java.util.ResourceBundle;

public class ChineseTranslate {

	private String resourceName = "RBExample";
    private ResourceBundle rb;
	public ArrayList<FoodDisplayObject> translateFoodDisplayObject(ArrayList<FoodDisplayObject> fDO){
		rb = ResourceBundle.getBundle(resourceName,  Locale.forLanguageTag("cn")); 
		ArrayList<FoodDisplayObject> tempFDOList = fDO;
		for(FoodDisplayObject f: tempFDOList){
			f.getFoodOrderItem();
		}
		
		return null;
	}
    
}
