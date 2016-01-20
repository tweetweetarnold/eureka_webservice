package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Represents the chosen Add-ons of the Food items entity model in the web application
 * 
 * @author SMU Team Eureka
 * 
 */
@Entity
@Table(name = "modifierchosen")
public class ModifierChosen {

	private Date createDate;
	private String description;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "foodOrderItemId")
	private FoodOrderItem foodOrderItem;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int modifierChosenId;
	private String name;
	private double price;

	/**
	 * Creates a default constructor for ModifierChosen
	 */
	public ModifierChosen() {
	}

	/**
	 * Creates a new ModifierChosen with the Modifier and FoodOrderItem
	 * 
	 * @param m The designated Modifier object
	 * @param item The designated FoodOrderItem object
	 */
	public ModifierChosen(Modifier m, FoodOrderItem item) {
		this.name = m.getName();
		this.description = m.getDescription();
		this.price = m.getPrice();
		this.createDate = new Date();
		this.foodOrderItem = item;
	}

	// Check if otherModifier equal current modifier
	/**
	 * Checks if this ModifierChosen is the same as the other ModifierChosen
	 * 
	 * @param otherModifier The ModifierChosen to be compared with
	 * @return Returns true if this ModifierChosen and the other ModifierChosen is the same
	 */
	public boolean equals(ModifierChosen otherModifier) {
		if (this.modifierChosenId == otherModifier.getModifierId()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Retrieves the date which this ModifierChosen object is created
	 * 
	 * @return The date that this ModifierChosen object is created
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Retrieves the description of the ModifierChosen
	 * 
	 * @return The ModifierChosen's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Retrieves the FoodOrderItem of this ModifierChosen
	 * 
	 * @return The FoodOrderItem that has this ModifierChosen
	 */
	public FoodOrderItem getFood() {
		return foodOrderItem;
	}

	/**
	 * Retrieves the ID of the ModifierChosen
	 * 
	 * @return The ModifierChosen's ID
	 */
	public int getModifierId() {
		return modifierChosenId;
	}

	/**
	 * Retrieves the name of the ModifierChosen
	 * 
	 * @return The ModifierChosen's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the price of the ModifierChosen
	 * 
	 * @return The price of the ModifierChosen
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Changes the current date of this Modifier object created
	 * 
	 * @param createDate The new date of this ModifierChosen object being created
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Changes the current description of the ModifierChosen
	 * 
	 * @param description The new description of the ModifierChosen
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Changes the FoodOrderItem for this ModifierChosen
	 * 
	 * @param foodOrderItem The new FoodOrderItem that has this ModifierChosen
	 */
	public void setFood(FoodOrderItem foodOrderItem) {
		this.foodOrderItem = foodOrderItem;
	}

	/**
	 * Changes the current ID of the ModifierChosen
	 * 
	 * @param modifierChosenId The new ID of the ModifierChosen
	 */
	public void setModifierId(int modifierChosenId) {
		this.modifierChosenId = modifierChosenId;
	}

	/**
	 * Changes the current name of the ModifierChosen
	 * 
	 * @param name The ModifierChosen's new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Changes the current price of the ModifierChosen
	 * 
	 * @param price The new price of the ModifierChosen
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
