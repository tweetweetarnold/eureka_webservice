package model;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents the Food order item(s) entity model in the web application
 * 
 * @author SMU Team Eureka
 *
 */
@Entity
@Table(name = "foodorderitem")
public class FoodOrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodOrderItemId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "foodOrderId")
	private FoodOrder foodOrder;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "foodId")
	private Food food;
	private int quantity;
	private String remarks;
	private Date createDate;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "foodOrderItem")
	private Set<ModifierChosen> modifierChosenList;
	
	/**
	 * Creates a default constructor for FoodOrderItem
	 */
	public FoodOrderItem() {
	}
	
	/**
	 * Creates a new FoodOrderItem with the foodOrder, food, quantity and its remarks
	 * 
	 * @param foodOrder The designated foodOrder of this FoodOrderItem
	 * @param food The designated food in this FoodOrderItem
	 * @param quantity The quantity of this FoodOrderItem
	 * @param remarks The remarks in this FoodOrderItem
	 */
	public FoodOrderItem(FoodOrder foodOrder, Food food, int quantity, String remarks) {
		super();
		this.foodOrder = foodOrder;
		this.food = food;
		this.quantity = quantity;
		this.remarks = remarks;
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Singapore"));
		this.createDate = cal.getTime();
		modifierChosenList = new HashSet<>();
	}
	
	/**
	 * Retrieves the Set of selected Modifiers in the FoodOrderItem
	 * 
	 * @return A Set of ModifierChosen objects in this FoodOrderItem
	 */
	public Set<ModifierChosen> getModifierChosenList() {
		return modifierChosenList;
	}
	
	/**
	 * Changes the Set of selected Modifiers in the FoodOrderItem
	 * 
	 * @param modifierChosenList The new Set of ModifierChosen objects
	 */
	public void setModifierChosenList(Set<ModifierChosen> modifierChosenList) {
		this.modifierChosenList = modifierChosenList;
	}
	
	/**
	 * Retrieves the ID of the FoodOrderItem
	 * 
	 * @return The FoodOrderItem ID
	 */
	public int getFoodOrderItemId() {
		return foodOrderItemId;
	}
	
	/**
	 * Changes the current ID of the FoodOrderItem ID
	 * 
	 * @param foodOrderItemId The new FoodOrderItem ID
	 */
	public void setFoodOrderItemId(int foodOrderItemId) {
		this.foodOrderItemId = foodOrderItemId;
	}
	
	/**
	 * Retrieves the Food order of this FoodOrderItem
	 * 
	 * @return The FoodOrder object
	 */
	public FoodOrder getFoodOrder() {
		return foodOrder;
	}
	
	/**
	 * Changes the FoodOrder of this FoodOrderItem
	 * 
	 * @param foodOrder The new FoodOrder object
	 */
	public void setFoodOrder(FoodOrder foodOrder) {
		this.foodOrder = foodOrder;
	}
	
	/**
	 * Retrieves the Food in the FoodOrderItem
	 * 
	 * @return The Food object in this FoodOrderItem
	 */
	public Food getFood() {
		return food;
	}
	
	/**
	 * Changes the Food in the FoodOrderItem
	 * 
	 * @param food The new Food object in this FoodOrderItem
	 */
	public void setFood(Food food) {
		this.food = food;
	}
	
	/**
	 * Retrieves the quantity of the FoodOrderItem
	 * 
	 * @return The current quantity of this FoodOrderItem
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Changes the quantity of the FoodOrderItem
	 * 
	 * @param quantity The new quantity of this FoodOrderItem
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Retrieves the price of the FoodOrderItem 
	 * 
	 * @return The current price of this FoodOrderItem (with the price of the Modifier(s))
	 */
	public double getPrice() {
		double price = food.getPrice();

		if (!modifierChosenList.isEmpty()) {
			Iterator<ModifierChosen> iter = modifierChosenList.iterator();

			while (iter.hasNext()) {
				ModifierChosen tempMod = (ModifierChosen) iter.next();
				price += tempMod.getPrice();
			}
		}
		return price;
	}
	
	/**
	 * Formats the price value into 2 decimal place
	 * 
	 * @return The price value in 2 decimal places
	 */
	public String getPriceString() {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(getPrice());
	}
	
	/**
	 * Retrieves the remarks of the FoodOrderItem
	 * 
	 * @return The remarks of this FoodOrderItem
	 */
	public String getRemarks() {
		return remarks;
	}
	
	/**
	 * Changes the remarks of the FoodOrderItem
	 * 
	 * @param remarks The new remarks of this FoodOrderItem
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * Retrieves the date which this FoodOrderItem object is created
	 * 
	 * @return The date that this FoodOrderItem object being created
	 */
	public Date getCreateDate() {
		return createDate;
	}
	
	/**
	 * Changes the current date of this FoodOrderItem object being created
	 * 
	 * @param createDate The new date of this FoodOrderItem object being created
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * Checks if this FoodOrderItem is the same as the other FoodOrderItem
	 * 
	 * @param other The FoodOrderItem to be compared with
	 * @return Returns true if this FoodOrderItem is the same
	 */
	public boolean equals2(FoodOrderItem other) {
		// check if same food
		if (other.getFood().equals(food)) {

			// check which one has longer modifierchosenlist
			int listALength = modifierChosenList.size();
			int listBLength = other.getModifierChosenList().size();

			// check length, if same, continue
			if (listALength == listBLength) {

				Set<ModifierChosen> listA = modifierChosenList;
				Set<ModifierChosen> listB = other.getModifierChosenList();

				int counter = 0;
				for (ModifierChosen c1 : listA) {
					for (ModifierChosen c2 : listB) {
						if (c1.getName().equals(c2.getName())) {
							counter++;
						}
					}
				}

				if (counter == listA.size()) {
					return true;
				}
			}
		}
		return false;
	}

}
