package model;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodDisplayObject {
	private int serialNumber;
	private String stallName;
	private ArrayList<FoodOrderItem> foodOrderItemList;
	private HashMap<FoodOrderItem, Integer> quantity;
	private HashMap<FoodOrderItem, ArrayList<String>> username;
	
	public FoodDisplayObject(int serialNumber){
		this.serialNumber =serialNumber;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getStallName() {
		return stallName;
	}

	public void setStallName(String stallName) {
		this.stallName = stallName;
	}

	public ArrayList<FoodOrderItem> getFoodOrderItem() {
		return foodOrderItemList;
	}

	public void setFoodOrderItem(ArrayList<FoodOrderItem> foodOrderItemList) {
		this.foodOrderItemList = foodOrderItemList;
	}

	public void setQuantity() {
		for(FoodOrderItem f : foodOrderItemList){
			FoodOrderItem foodItemd = f;
			if(quantity.containsKey(foodItemd)){
				int quant = quantity.get(foodItemd);
				quant++;
				quantity.put(foodItemd,quant);
			}else{
				quantity.put(foodItemd, 1);
			}
//			Food tempFood = foodItemd.getFood();
//			Stall tempStall = tempFood.getStall();
//			ArrayList<FoodOrderItem> tempFoodOrderItemList = new ArrayList<FoodOrderItem>();
		}
	}
	
	public HashMap<FoodOrderItem, Integer> getQuantity() {
		return quantity;
	}


	public HashMap<FoodOrderItem, ArrayList<String>> getUsername() {
		return username;
	}

	public void setUsername(HashMap<FoodOrderItem, ArrayList<String>> username) {
		this.username = username;
	}
	
	
	
	
	
}
