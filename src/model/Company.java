package model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents a Company entity model in the web application
 * 
 * @author SMU Team Eureka
 */
@Entity
@Table(name = "company")
public class Company {
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<Canteen> canteenList;
	private String companyCode;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyId;
	private Date createDate;
	private Date cutoffTime;
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> deliveryPointSet;
	private String name;

	/**
	 * Creates a default constructor for Company
	 */
	public Company() {
	}

	/**
	 * Creates a new Company with a given name, cut-off timing, the list of Canteen and a company
	 * code
	 * 
	 * @param name The name of the Company
	 * @param cutoffTime The cut-off timing for ordering food
	 * @param canteenList The list of Canteen
	 * @param companyCode The company code assigned to the Company
	 */
	public Company(String name, Date cutoffTime, Set<Canteen> canteenList, String companyCode) {
		super();
		this.name = name;
		this.createDate = new Date();
		this.cutoffTime = cutoffTime;
		this.canteenList = canteenList;
		this.companyCode = companyCode;
	}

	/**
	 * Retrieves the current list of Canteens
	 * 
	 * @return The list of Canteens
	 */
	public Set<Canteen> getCanteenList() {
		return canteenList;
	}

	/**
	 * Retrieves the current company code of the Company
	 * 
	 * @return The company code
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * Retrieves the current ID of the Company
	 * 
	 * @return The company ID
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * Retrieves the date of the Company's entity model created
	 * 
	 * @return The date which the Company entity was created
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Retrieves the Company's current cut-off timing for ordering food
	 * 
	 * @return The cut-off timing for ordering food
	 */
	public Date getCutoffTime() {
		return cutoffTime;
	}

	/**
	 * Retrieves the current set of Delivery Point for the Company
	 * 
	 * @return The set of Delivery Point
	 */
	public Set<String> getDeliveryPointSet() {
		return deliveryPointSet;
	}

	/**
	 * Retrieves the current name of the Company
	 * 
	 * @return The name of the Company
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the list of Canteens with a new list of Canteens
	 * 
	 * @param canteenList The new list of Canteens
	 */
	public void setCanteenList(Set<Canteen> canteenList) {
		this.canteenList = canteenList;
	}

	/**
	 * Changes the company code with a new company code
	 * 
	 * @param companyCode The new company code
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * Change the Company's cut-off timing with a new cut-off timing
	 * 
	 * @param cutoffTime The new cut-off timing
	 */
	public void setCutoffTime(Date cutoffTime) {
		this.cutoffTime = cutoffTime;
	}

	/**
	 * Changes the set of Delivery Point
	 * 
	 * @param deliveryPointSet The new set of Delivery Point
	 */
	public void setDeliveryPointSet(Set<String> deliveryPointSet) {
		this.deliveryPointSet = deliveryPointSet;
	}

	/**
	 * Changes the name of the company with the new Company name
	 * 
	 * @param name The new Company name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
