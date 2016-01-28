package model;

import java.util.Date;
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
 * Represents the Stall entity model in the web application
 * 
 * @author SMU Team Eureka
 */
@Entity
@Table(name = "stall")
public class Stall {
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "canteenId")
	private Canteen canteen;
	private long contactNo;
	private Date createDate;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "stall")
	private Set<Food> foodList;
	private String imageDirectory;
	private String name;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stallId;
	private String status;

	/**
	 * Creates a default constructor for Stall
	 */
	public Stall() {
		this.status = "Ok";
	}

	/**
	 * Creates a new Stall with its name, contact number, canteen, list of food, the image directory
	 * 
	 * @param name The name of the Stall
	 * @param contactNo The contact number of the Stall
	 * @param canteen The Canteen which the Stall belongs to
	 * @param foodList The list of Food which the Stall sells
	 * @param imageDirectory The directory of storing the images of the Food
	 */
	public Stall(String name, long contactNo, Canteen canteen, Set<Food> foodList,
			String imageDirectory) {
		super();
		this.name = name;
		this.contactNo = contactNo;
		this.imageDirectory = imageDirectory;
		this.canteen = canteen;
		this.createDate = new Date();
		this.foodList = foodList;
		this.status = "Ok";
	}

	/**
	 * Retrieves the Canteen of this Stall
	 * 
	 * @return The Canteen which this Stall belongs to
	 */
	public Canteen getCanteen() {
		return canteen;
	}

	/**
	 * Retrieves the contact number of the Stall
	 * 
	 * @return The Stall's contact number
	 */
	public long getContactNo() {
		return contactNo;
	}

	/**
	 * Retrieves the date of the Stall's entity model created
	 * 
	 * @return The date which the Stall's entity was created
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Retrieves the list of Food that the Stall sells
	 * 
	 * @return The Stall's Food list
	 */
	public Set<Food> getFoodList() {
		return foodList;
	}

	/**
	 * Retrieves the directory that stores the Food images
	 * 
	 * @return The directory for storing the Food images
	 */
	public String getImageDirectory() {
		return imageDirectory;
	}

	/**
	 * Retrieves the name of the Stall
	 * 
	 * @return The Stall's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the ID of the Stall
	 * 
	 * @return The Stall's ID
	 */
	public int getStallId() {
		return stallId;
	}

	public String getStatus() {
		return status;
	}

	/**
	 * Changes the Canteen of this Stall
	 * 
	 * @param canteen The new Canteen which this Stall belongs to
	 */
	public void setCanteen(Canteen canteen) {
		this.canteen = canteen;
	}

	/**
	 * Changes the contact number of the Stall with a new number
	 * 
	 * @param contactNo The Stall's new contact number
	 */
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * Changes the date of the Stall's entity model created
	 * 
	 * @param createDate The new date of the Stall entity model being created
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Changes the current list of Food that the Stall sells
	 * 
	 * @param foodList The list of new Food to be updated in the Stall
	 */
	public void setFoodList(Set<Food> foodList) {
		this.foodList = foodList;
	}

	/**
	 * Changes the directory that stores the Food images
	 * 
	 * @param imageDirectory The new directory foe storing the Food images
	 */
	public void setImageDirectory(String imageDirectory) {
		this.imageDirectory = imageDirectory;
	}

	/**
	 * Changes the name of the Stall
	 * 
	 * @param name The Stall's new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Changes the ID of the Stall
	 * 
	 * @param stallId The Stall's new ID
	 */
	public void setStallId(int stallId) {
		this.stallId = stallId;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
