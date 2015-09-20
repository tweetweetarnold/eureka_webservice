package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.JodaTimePermission;

import dao.FoodOrderDAO;
import entity.FoodOrder;

public class FoodOrderController {
	FoodOrderDAO foodOrderDAO = new FoodOrderDAO();
	
	public FoodOrderController(){
		
	}
	
	public void addFoodOrder(FoodOrder f){
		foodOrderDAO.saveFoodOrder(f);
	}
	
	public FoodOrder getFoodOrder(int foodOrderId){
		return foodOrderDAO.getFoodOrder(foodOrderId);
	}
	
	public List getFoodOrderToday(){
		
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(cal.getTime());
        System.out.println("Current date in String Format: " + strDate);
		SimpleDateFormat sdf1 = new SimpleDateFormat();
        sdf1.applyPattern("yyyy-MM-dd");
        Date date= null;
		try {
			date = sdf1.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String string=sdf1.format(date);
        System.out.println("Current date in Date Format: " + string);

		
		
		System.out.println(string);
		
		
		
		return foodOrderDAO.getFoodOrderByDate(string);
	}
	
}
