package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import value.StringValues;

/**
 * Represents a Employee entity model in the web application
 * 
 * @author SMU Team Eureka
 */
@Entity
@Table(name = "employee")
public class Employee {

	private double amountOwed;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "companyId")
	private Company company;
	private long contactNo;
	private Date createDate;
	private String deliveryPoint;
	@Id
	private String email;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "foodId")
	private Food favoriteFood;
	private String password, name;
	private String status;

	/**
	 * Creates a new default constructor for Employee
	 */
	public Employee() {
	}

	/**
	 * Creates a new Employee with a password, name, email address, contact number and its company
	 * 
	 * @param password The Employee's password
	 * @param name The Employee's name
	 * @param email The Employee's email address
	 * @param contactNo The Employee's contact number
	 * @param company The Employee's company
	 */
	public Employee(String password, String name, String email, long contactNo, Company company) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.amountOwed = 0;
		this.contactNo = contactNo;
		this.company = company;
		this.status = StringValues.EMPLOYEE_ACTIVE;
		this.createDate = new Date();
		this.favoriteFood = null;

	}

	/**
	 * Retrieves the current amount owed by the Employee
	 * 
	 * @return The amount owed by the Employee
	 */
	public double getAmountOwed() {
		return amountOwed;
	}

	/**
	 * Retrieves the company of the Employee
	 * 
	 * @return The Employee's company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Retrieves the contact number of the Employee
	 * 
	 * @return The Employee's contact number
	 */
	public long getContactNo() {
		return contactNo;
	}

	/**
	 * Retrieves the date of the Employee's entity model created
	 * 
	 * @return The date which the Employee entity was created
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Retrieves the Employee's default delivery point
	 * 
	 * @return The Employee's current default delivery point
	 */
	public String getDeliveryPoint() {
		return deliveryPoint;
	}

	/**
	 * Retrieves the email address of the Employee
	 * 
	 * @return The Employee's email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns the favorite food of the Employee
	 * 
	 * @return The food which Employee indicates as their favorite
	 */
	public Food getFavoriteFood() {
		return favoriteFood;
	}

	/**
	 * Retrieves the name of the Employee
	 * 
	 * @return The Employee's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the password of the Employee
	 * 
	 * @return The Employee's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Retrieves the current status of the Employee
	 * 
	 * @return The status of the Employee
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Changes the amount owed with a new amount
	 * 
	 * @param amountOwed The updated amount owed by the Employee
	 */
	public void setAmountOwed(double amountOwed) {
		this.amountOwed = amountOwed;
	}

	/**
	 * Changes the company of the Employee
	 * 
	 * @param company The Employee's new company
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Changes the contact number of the Employee with a new number
	 * 
	 * @param contactNo The Employee's new contact number
	 */
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * Changes the date of the Employee's entity model created
	 * 
	 * @param createDate The new date of the Employee's entity model being created
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Changes the Employee's default delivery point
	 * 
	 * @param deliveryPoint The Employee's new default delivery point
	 */
	public void setDeliveryPoint(String deliveryPoint) {
		this.deliveryPoint = deliveryPoint;
	}

	/**
	 * Changes the email address of Employee with a new email address
	 * 
	 * @param email The Employee's new email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Changes the favorite food of the Employee
	 * 
	 * @param favoriteFood The Employee's new favorite Food
	 */
	public void setFavoriteFood(Food favoriteFood) {
		this.favoriteFood = favoriteFood;
	}

	/**
	 * Changes the current name of the Employee
	 * 
	 * @param name The new Employee's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Changes the current password with a new password
	 * 
	 * @param password The Employee's new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Changes the status of the Employee
	 * 
	 * @param status The new status can be "Ok", "Suspended" or "Pending verification"
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
