package model;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodDisplayObject {
	public int serialNumber;
	public String stallName;
	public ArrayList<FoodOrderItem> foodOrderItemList = new ArrayList<FoodOrderItem>();
	public HashMap<Integer, Integer> quantityList = new HashMap<Integer, Integer>();
	public HashMap<Integer, ArrayList<String>> usernameList = new HashMap<Integer, ArrayList<String>>();

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

	public ArrayList<FoodOrderItem> getFoodOrderItemList() {
		return foodOrderItemList;
	}

	public void setFoodOrderItemList(ArrayList<FoodOrderItem> foodOrderItemList) {
		this.foodOrderItemList = foodOrderItemList;
	}

	public HashMap<Integer, Integer> getQuantityList() {
		return quantityList;
	}

	public void setQuantityList(HashMap<Integer, Integer> quantityList) {
		this.quantityList = quantityList;
	}

	public HashMap<Integer, ArrayList<String>> getUsernameList() {
		return usernameList;
	}

	public void setUsernameList(HashMap<Integer, ArrayList<String>> usernameList) {
		this.usernameList = usernameList;
	}

}
