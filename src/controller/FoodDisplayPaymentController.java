package controller;

import java.util.ArrayList;
import java.util.List;
import model.FoodDisplayPayment;
import model.FoodOrder;

public class FoodDisplayPaymentController {
	public ArrayList<FoodDisplayPayment> renderFoodDisplayPaymentList(List<FoodOrder> foodOrderList){
		ArrayList<FoodDisplayPayment> foodDisplayPaymentList = new ArrayList<FoodDisplayPayment>();
		for(FoodOrder foodOrder: foodOrderList){
			FoodDisplayPayment foodDisplayPayment = new FoodDisplayPayment(foodOrder);
			foodDisplayPaymentList.add(foodDisplayPayment);
		}
		return foodDisplayPaymentList;
	}
}
