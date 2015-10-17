package test;

import java.util.ArrayList;

import controller.FoodOrderController;
import model.FoodOrder;

public class Test2 {

	public static void main(String[] args) {
		
		FoodOrderController foodOrderController = new FoodOrderController();
		ArrayList<FoodOrder> foodOrderList = new ArrayList<FoodOrder>(foodOrderController.getFoodOrderBetweenCutOff());

		
		System.out.println("Test2.java completed");

	}

}
