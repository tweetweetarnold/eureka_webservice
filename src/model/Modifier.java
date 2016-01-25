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

import value.StringValues;

/**
 * Represents the Add-Ons of the Food items entity model in the web application
 * 
 * @author SMU Team Eureka
 */
@Entity
@Table(name = "modifier")
public class Modifier {
	private Date createDate;
	private String description;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "foodId")
	private Food food;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int modifierId;
	private String name, status;
	private double price;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "modifierSectionId")
	private ModifierSection modifierSection;

	/**
	 * Creates a default constructor for Modifier
	 */
	public Modifier() {
	}

	/**
	 * Creates a new Modifier object with a name, description, price and the Food
	 * 
	 * @param name The name of the Modifier
	 * @param description The description of the Modifier
	 * @param price The price of the Modifier
	 * @param food The Food that contains this Modifier
	 */
	public Modifier(String name, String description, double price, Food food) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.food = food;
		this.createDate = new Date();
		this.status = StringValues.ACTIVE;
	}
	
	public Modifier(String name, String description, double price, Food food, ModifierSection modifierSection) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.food = food;
		this.createDate = new Date();
		this.modifierSection = modifierSection;
	}
	// Check if otherModifier equal current modifier
	/**
	 * Checks if this Modifier is the same as the other Modifier
	 * 
	 * @param otherModifier The Modifier to be compared with
	 * @return Returns true if this Modifier and the other Modifier is the same
	 */
	public boolean equals(Modifier otherModifier) {
		if (this.food.equals(otherModifier.getFood())) {
			if (this.name.equals(otherModifier.getName())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * Retrieves the date which this Modifier object is created
	 * 
	 * @return The date that this Modifier object is created
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Retrieves the description of the Modifier
	 * 
	 * @return The description of the Modifier
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Retrieves the Food of this Modifier
	 * 
	 * @return The Food that has this Modifier
	 */
	public Food getFood() {
		return food;
	}

	/**
	 * Retrieves the ID of the Modifier
	 * 
	 * @return The Modifier's ID
	 */
	public int getModifierId() {
		return modifierId;
	}

	/**
	 * Retrieves the name of the Modifier
	 * 
	 * @return The Modifier's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the price of the Modifier
	 * 
	 * @return The price of the Modifier
	 */
	public double getPrice() {
		return price;
	}

	public String getStatus() {
		return status;
	}

	/**
	 * Changes the current date of this Modifier object created
	 * 
	 * @param createDate The new date of this Modifier object being created
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Changes the current description of the Modifier
	 * 
	 * @param description The new description of the Modifier
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Changes the Food for this Modifier
	 * 
	 * @param food The new Food that has this Modifier
	 */
	public void setFood(Food food) {
		this.food = food;
	}

	/**
	 * Changes the current ID of the Modifier
	 * 
	 * @param modifierId The new Modifier ID
	 */
	public void setModifierId(int modifierId) {
		this.modifierId = modifierId;
	}

	/**
	 * Changes the name of the Modifier
	 * 
	 * @param name The new name of the Modifier
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Changes the current price of the Modifier
	 * 
	 * @param price The new price of the Modifier
	 */
	public void setPrice(double price) {
		this.price = price;
	}


	public ModifierSection getModifierSection() {
		return modifierSection;
	}

	public void setModifierSection(ModifierSection modifierSection) {
		this.modifierSection = modifierSection;
	}
	

	public void setStatus(String status) {
		this.status = status;
	}


}
