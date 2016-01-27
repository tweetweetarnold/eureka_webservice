package model;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
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

import value.StringValues;

/**
 * Represents the Food entity model in the web application
 * 
 * @author SMU Team Eureka
 */
@Entity
@Table(name = "food")
public class Food {
	private Date createDate;
	private String description;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodId;
	private String imageDirectory;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "food")
	private Set<Modifier> modifierList;
	private String name, status;
	private double price;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "stallId")
	private Stall stall;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "food")
	private Set<ModifierSection> modifierSectionList;
	private String weatherConditions;
	private String publicId;

	/**
	 * Creates a default constructor for Food
	 */
	public Food() {
	}

	/**
	 * Creates a new Food object with a name, food description, price, the image directory and the
	 * Stall
	 * 
	 * @param name The name of the Food
	 * @param description The description of the Food
	 * @param price The price of the Food
	 * @param imageDirectory The directory where the Food image is being stored
	 * @param stall The Stall that sells this Food
	 */
	public Food(String name, String description, double price, String imageDirectory,
			String publicId, Stall stall) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.stall = stall;
		this.imageDirectory = imageDirectory;
		this.createDate = new Date();
		this.modifierList = new HashSet<>();
		this.status = StringValues.ACTIVE;
		this.weatherConditions = "default";
		this.publicId = publicId;
	}

	// check if canteen, stall and food name are the same and returns true if
	// they are all the same
	/**
	 * Checks if this Food's name and its Canteen and Stall's name are the same as the other Food
	 * 
	 * @param otherFood The Food to be compared with
	 * @return Returns true if this Food's name and its Canteen and Stall's name are the same
	 */
	public boolean equals(Food otherFood) {
		if (this.stall.getCanteen().getName().equals(otherFood.getStall().getCanteen().getName())) {
			if (this.stall.getName().equals(otherFood.getStall().getName())) {
				if (this.name.equals(otherFood.getName())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Retrieves the date which this Food object is created
	 * 
	 * @return The date that this Food object is created
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Retrieves the description of the Food
	 * 
	 * @return The description of the Food
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Retrieves the ID of the Food
	 * 
	 * @return The Food's ID
	 */
	public int getFoodId() {
		return foodId;
	}

	/**
	 * Retrieves the directory of storing the Food image
	 * 
	 * @return The directory of storing the Food image
	 */
	public String getImageDirectory() {
		return imageDirectory;
	}

	/**
	 * Retrieves the list of Modifiers available in this Food
	 * 
	 * @return The list of Modifiers in this Food
	 */
	public Set<Modifier> getModifierList() {
		return modifierList;
	}

	/**
	 * Retrieves the name of this Food
	 * 
	 * @return The name of the Food
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the price of the Food
	 * 
	 * @return The price of the Food
	 */
	public double getPrice() {
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
	 * Retrieves the Stall of this Food
	 * 
	 * @return The Stall which sells this Food
	 */
	public Stall getStall() {
		return stall;
	}

	public String getStatus() {
		return status;
	}

	/**
	 * Retrieves the weather condition indicated in this Food
	 * 
	 * @return The current weather condition
	 */
	public String getWeatherConditions() {
		return weatherConditions;
	}

	/**
	 * Changes the current date of this Food object being created
	 * 
	 * @param createDate The new date of this Food object being created
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Changes the current description of the Food
	 * 
	 * @param description The new description of the Food
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Changes the current Food ID
	 * 
	 * @param foodId The Food's new ID
	 */
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	/**
	 * Changes the current directory of storing the Food image
	 * 
	 * @param imageDirectory The new directory where the Food image is stored
	 */
	public void setImageDirectory(String imageDirectory) {
		this.imageDirectory = imageDirectory;
	}

	/**
	 * Changes the current list of Modifiers in this Food
	 * 
	 * @param modifierList The new list of Modifiers for this Food
	 */
	public void setModifierList(Set<Modifier> modifierList) {
		this.modifierList = modifierList;
	}

	/**
	 * Changes the current name of this Food
	 * 
	 * @param name The new name of the Food
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Changes the current price of the Food
	 * 
	 * @param price The new price of the Food
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Changes the current Stall of this Food
	 * 
	 * @param stall The new Stall of this Food
	 */
	public void setStall(Stall stall) {
		this.stall = stall;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Changes the current weather condition indicated in this Food
	 * 
	 * @param weatherConditions The new weather condition
	 */
	public void setWeatherConditions(String weatherConditions) {
		this.weatherConditions = weatherConditions;
	}

	public Set<ModifierSection> getModifierSectionList() {
		return modifierSectionList;
	}

	public void setModifierSectionList(Set<ModifierSection> modifierSectionList) {
		this.modifierSectionList = modifierSectionList;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;

	}

	public String getPublicId() {
		return publicId;
	}

}
