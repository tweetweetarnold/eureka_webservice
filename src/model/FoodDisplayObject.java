package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the entity model of food order display on the Administrator side (by Stalls) in the
 * web application
 * 
 * @author SMU Team Eureka
 * 
 */
public class FoodDisplayObject {
	public ArrayList<FoodOrderItem> foodOrderItemList = new ArrayList<FoodOrderItem>();
	public long phoneNumber;
	public HashMap<Integer, Double> priceList = new HashMap<Integer, Double>();
	public HashMap<Integer, Integer> quantityList = new HashMap<Integer, Integer>();
	public int serialNumber;
	public String stallName;
	public double totalPrice;
	public HashMap<Integer, ArrayList<Employee>> usernameList = new HashMap<Integer, ArrayList<Employee>>();

	/**
	 * Creates a default constructor for FoodDisplayObject
	 */
	public FoodDisplayObject() {

	}

	/**
	 * Creates a FoodDisplayObject with a serial number
	 * 
	 * @param serialNumber The serialNumber for uniquely identifying this FoodDisplayObject
	 */
	public FoodDisplayObject(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * Retrieves the list of FoodOrderItems in the FoodDisplayObject
	 * 
	 * @return An ArrayList of FoodOrderItem objects in the FoodDisplayObject
	 */
	public ArrayList<FoodOrderItem> getFoodOrderItemList() {
		return foodOrderItemList;
	}

	/**
	 * Retrieves the contact number of the Stall in the FoodDisplayObject
	 * 
	 * @return The Stall's contact number
	 */
	public long getPhoneNumber() {
		return phoneNumber;
	}

	// FoodOrderItems to prices
	/**
	 * Retrieves a HashMap of the FoodOrderItems and its prices
	 * 
	 * @return A HashMap with the FoodOrderItem ID as the key and price as the corresponding value
	 */
	public HashMap<Integer, Double> getPriceList() {
		return priceList;
	}

	/**
	 * Retrieves a HashMap of the FoodOrderItems and its quantity
	 * 
	 * @return A HashMap featuring FoodOrderItem ID as the key and quantity as the corresponding
	 *         value
	 */
	public HashMap<Integer, Integer> getQuantityList() {
		return quantityList;
	}

	/**
	 * Retrieves the serial number of the FoodDisplayObject
	 * 
	 * @return The serial number of the FoodDisplayObject
	 */
	public int getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Retrieves the Stall name in the FoodDisplayObject
	 * 
	 * @return The name of the Stall in the FoodDisplayObject
	 */
	public String getStallName() {
		return stallName;
	}

	/**
	 * Retrieves the total price of the FoodOrderItem prices in the FoodDisplayObject
	 * 
	 * @return The total price of the FoodOrderItem prices in the FoodDisplayObject
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	// FoodOrderItem to arrayList of Users who ordered it
	/**
	 * Retrieves a HashMap of FoodOrderItems and the list of Employees whom ordered the
	 * FoodOrderItems
	 * 
	 * @return A HashMap with the FoodOrderItem ID as the key and an ArrayList of Employee objects
	 *         as the corresponding value
	 */
	public HashMap<Integer, ArrayList<Employee>> getUsernameList() {
		return usernameList;
	}

	/**
	 * Changes the current list of FoodOrderItems in the FoodDisplayObject
	 * 
	 * @param foodOrderItemList The new list of FoodOrderItem objects
	 */
	public void setFoodOrderItemList(ArrayList<FoodOrderItem> foodOrderItemList) {
		this.foodOrderItemList = foodOrderItemList;
	}

	/**
	 * Updates the current contact number of the Stall in the FoodDisplayObject
	 * 
	 * @param phoneNumber The new contact number of Stall
	 */
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Changes the current HashMap of the FoodOrderItems and its prices
	 * 
	 * @param priceList A HashMap of the updated or new FoodOrderItems and its prices
	 */
	public void setPriceList(HashMap<Integer, Double> priceList) {
		this.priceList = priceList;
	}

	// foodOrderItem to Qty
	/**
	 * Changes the current HashMap of the FoodOrderItems and its quantity
	 * 
	 * @param quantityList A HashMap of the updated or new FoodOrderItems and its quantity
	 */
	public void setQuantityList(HashMap<Integer, Integer> quantityList) {
		this.quantityList = quantityList;
	}

	/**
	 * Changes the current serial number of the FoodDisplayObject
	 * 
	 * @param serialNumber The new serial number of the FoodDisplayObject
	 */
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * Changes the current Stall name in the FoodDisplayObject
	 * 
	 * @param stallName The new name of the Stall in the FoodDisplayObject
	 */
	public void setStallName(String stallName) {
		this.stallName = stallName;
	}

	/**
	 * Changes the current total price of the FoodOrderItem prices in the FoodDisplayObject
	 * 
	 * @param totalPrice The new total price of the FoodOrderItem prices in the FoodDisplayObject
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * Changes the current HashMap of FoodOrderItems and the list of Employees whom ordered the
	 * FoodOrderItems
	 * 
	 * @param usernameList A HashMap of the updated or new FoodOrderItems and the list of Employees
	 *            whom ordered the FoodOrderItems
	 */
	public void setUsernameList(HashMap<Integer, ArrayList<Employee>> usernameList) {
		this.usernameList = usernameList;
	}

}
