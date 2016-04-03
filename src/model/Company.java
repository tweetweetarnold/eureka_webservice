package model;

import java.util.Date;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import value.StringValues;

/**
 * Represents a Company entity model in the web application
 * 
 * @author SMU Team Eureka
 */
@Entity
@Table(name = "company")
public class Company {
	private String companyCode;
	private String name, status;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyId;
	private Date createDate;
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> deliveryPointSet;

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
	public Company(String name, String companyCode) {
		super();
		this.name = name;
		this.createDate = new Date();
		this.companyCode = companyCode;
		this.status = StringValues.ACTIVE;
	}

	public Company(String name, String companyCode, Set<String> deliveryPointSet) {
		super();
		this.name = name;
		this.createDate = new Date();
		this.companyCode = companyCode;
		this.deliveryPointSet = deliveryPointSet;
		this.status = StringValues.ACTIVE;
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

	public String getStatus() {
		return status;
	}

	/**
	 * Changes the company code with a new company code
	 * 
	 * @param companyCode The new company code
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	/**
	 * Changes the status of the company to the new Status
	 * 
	 * @param status The new Status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
