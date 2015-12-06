package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FoodDisplayObject {
	private int serialNumber;
	private String stallName;
	private ArrayList<FoodOrderItem> foodOrderItemList = new ArrayList<FoodOrderItem>();
	private LinkedHashMap<FoodOrderItem, Integer> quantity = new LinkedHashMap<FoodOrderItem, Integer>();
	private LinkedHashMap<FoodOrderItem, ArrayList<String>> username = new LinkedHashMap<FoodOrderItem, ArrayList<String>>();

	public FoodDisplayObject(int serialNumber) {
		this.serialNumber = serialNumber;
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

	public void setQuantity(LinkedHashMap<FoodOrderItem, Integer> quantityToFoodOrderItem) {
		this.quantity = quantityToFoodOrderItem;
		// for(FoodOrderItem f : foodOrderItemList){
		// FoodOrderItem foodItemd = f;
		// if(quantity.containsKey(foodItemd)){
		// int quant = quantity.get(foodItemd);
		// quant++;
		// quantity.put(foodItemd,quant);
		// }else{
		// quantity.put(foodItemd, 1);
		// }
		// Food tempFood = foodItemd.getFood();
		// Stall tempStall = tempFood.getStall();
		// ArrayList<FoodOrderItem> tempFoodOrderItemList = new ArrayList<FoodOrderItem>();
		// }
	}

	public HashMap<FoodOrderItem, Integer> getQuantityList() {
		return quantity;
	}

	public int getQuantity(FoodOrderItem f) {
		return quantity.get(f);
	}

	public HashMap<FoodOrderItem, ArrayList<String>> getUsernameHash() {
		return username;
	}

	public ArrayList<String> getUsernameList(FoodOrderItem f) {
		return username.get(f);
	}

	public void setUsername(LinkedHashMap<FoodOrderItem, ArrayList<String>> username) {
		this.username = username;
	}

}
