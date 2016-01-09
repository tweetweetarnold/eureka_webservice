package model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents a Canteen entity model in the web application
 * 
 * @author SMU Team Eureka
 */
@Entity
@Table(name = "canteen")
public class Canteen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int canteenId;
	private String name;
	private String address;
	private Date createDate;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "canteen")
	private Set<Stall> stallList;

	/**
	 * Creates a default constructor for Canteen
	 */
	public Canteen() {
	}

	/**
	 * Creates a new Canteen with a name, address and a list of Stalls
	 * 
	 * @param name The name of the Canteen
	 * @param address The address of the Canteen
	 * @param stallList The list of Stalls available in this Canteen
	 */
	public Canteen(String name, String address, Set<Stall> stallList) {
		super();
		this.name = name;
		this.address = address;
		this.createDate = new Date();
		this.stallList = stallList;
	}

	/**
	 * Retrieves the name of the Canteen
	 * 
	 * @return The Canteen's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the name of the Canteen
	 * 
	 * @param name The Canteen's new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the ID of the Canteen
	 * 
	 * @return The Canteen's ID
	 */
	public int getCanteenId() {
		return canteenId;
	}

	/**
	 * Retrieves the address of the Canteen
	 * 
	 * @return The Canteen's address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Changes the address of the Canteen
	 * 
	 * @param address The Canteen's new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Retrieves the date of the Canteen's entity model created
	 * 
	 * @return The date which the Canteen entity was created
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Retrieves the list of current Stalls in the Canteen
	 * 
	 * @return The list of Stalls in the Canteen
	 */
	public Set<Stall> getStallList() {
		return stallList;
	}

	/**
	 * Changes the list of Stalls in the Canteen
	 * 
	 * @param stallList The list of new Stalls to be updated in the Canteen
	 */
	public void setStallList(Set<Stall> stallList) {
		this.stallList = stallList;
	}

}
