package model;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

	private Date createDate;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "foodId")
	private Food food;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "foodOrderId")
	private FoodOrder foodOrder;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodOrderItemId;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "foodOrderItem")
	private Set<ModifierChosen> modifierChosenList;
	private int quantity;
	private String remarks;

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
		this.createDate = new Date();
		modifierChosenList = new HashSet<>();
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

	/**
	 * Retrieves the date which this FoodOrderItem object is created
	 * 
	 * @return The date that this FoodOrderItem object being created
	 */
	public Date getCreateDate() {
		return createDate;
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
	 * Retrieves the Food order of this FoodOrderItem
	 * 
	 * @return The FoodOrder object
	 */
	public FoodOrder getFoodOrder() {
		return foodOrder;
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
	 * Retrieves the Set of selected Modifiers in the FoodOrderItem
	 * 
	 * @return A Set of ModifierChosen objects in this FoodOrderItem
	 */
	public Set<ModifierChosen> getModifierChosenList() {
		return modifierChosenList;
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
	 * Retrieves the quantity of the FoodOrderItem
	 * 
	 * @return The current quantity of this FoodOrderItem
	 */
	public int getQuantity() {
		return quantity;
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
	 * Changes the current date of this FoodOrderItem object being created
	 * 
	 * @param createDate The new date of this FoodOrderItem object being created
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	 * Changes the FoodOrder of this FoodOrderItem
	 * 
	 * @param foodOrder The new FoodOrder object
	 */
	public void setFoodOrder(FoodOrder foodOrder) {
		this.foodOrder = foodOrder;
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
	 * Changes the Set of selected Modifiers in the FoodOrderItem
	 * 
	 * @param modifierChosenList The new Set of ModifierChosen objects
	 */
	public void setModifierChosenList(Set<ModifierChosen> modifierChosenList) {
		this.modifierChosenList = modifierChosenList;
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
	 * Changes the remarks of the FoodOrderItem
	 * 
	 * @param remarks The new remarks of this FoodOrderItem
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
