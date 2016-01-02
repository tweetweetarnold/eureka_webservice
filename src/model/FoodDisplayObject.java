package model;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodDisplayObject {
	public int serialNumber;
	public long phoneNumber;
	public String stallName;
	public ArrayList<FoodOrderItem> foodOrderItemList = new ArrayList<FoodOrderItem>();
	public HashMap<Integer, Integer> quantityList = new HashMap<Integer, Integer>();
	public HashMap<Integer, ArrayList<Employee>> usernameList = new HashMap<Integer, ArrayList<Employee>>();
	public HashMap<Integer, Double> priceList = new HashMap<Integer, Double>();
	public double totalPrice;

	public FoodDisplayObject(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public HashMap<Integer, Double> getPriceList() {
		return priceList;
	}

	public void setPriceList(HashMap<Integer, Double> priceList) {
		this.priceList = priceList;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
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

	public HashMap<Integer, ArrayList<Employee>> getUsernameList() {
		return usernameList;
	}

	public void setUsernameList(HashMap<Integer, ArrayList<Employee>> usernameList) {
		this.usernameList = usernameList;
	}

}
