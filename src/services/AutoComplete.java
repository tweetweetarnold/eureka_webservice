package services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;

import connection.MyConnection;
import dao.CanteenDAO;
import dao.FoodDAO;
import model.Canteen;
import model.Food;

public class AutoComplete {
	private ArrayList<Food> foodList;
	private int sizeOfList;
	
	public AutoComplete(Canteen c){
		FoodDAO foodDAO = new FoodDAO();
		this.foodList = new ArrayList<Food>(foodDAO.getAllFoodFromCanteen(c));
		this.sizeOfList = foodList.size();
	}
	
	
	public ArrayList<Food> getAllFood(){
		ArrayList<Food> returnList = null;

		DetachedCriteria dc = DetachedCriteria.forClass(Food.class);
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		returnList = new ArrayList<Food>();

		for (Object o : l) {
			returnList.add((Food) o);
		}
		return returnList;
	}
	
	
	//for autoFill
	public List<String> getData(String query){
		
		List<String> foodNames = new ArrayList<String>();
		String foodName = null;
		for(int i=0; i<sizeOfList; i++) {
			foodName = foodList.get(i).getName().trim().toLowerCase();
            if(foodName.startsWith(query)) {
            	foodNames.add(foodList.get(i).getName().trim());
            }
        }
		return foodNames;
	}
}
