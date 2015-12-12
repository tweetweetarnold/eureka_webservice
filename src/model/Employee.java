/**
 * 
 */
package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import value.StringValues;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	// @Column(unique = true)
	private String email;
	private String password, name;
	private double amountOwed;
	private long contactNo;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "companyId")
	private Company company;
	// @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// private Set<Food> favouriteList;
	private String status;
	private Date createDate;
	private String deliveryPoint;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "foodId")
	private Food favoriteFood;

	public Food getFavoriteFood() {
		return favoriteFood;
	}

	public void setFavoriteFood(Food favoriteFood) {
		this.favoriteFood = favoriteFood;
	}

	public Employee() {
	}

	public Employee(String password, String name, String email, long contactNo, Company company) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.amountOwed = 0;
		this.contactNo = contactNo;
		this.company = company;
		this.status = StringValues.EMPLOYEE_OK;
		// this.favouriteList = new HashSet<>();
		this.createDate = new Date();
		this.favoriteFood = null;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getAmountOwed() {
		return amountOwed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAmountOwed(double amountOwed) {
		this.amountOwed = amountOwed;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getDefaultDeliveryPoint() {
		return deliveryPoint;
	}

	public void setDefaultDeliveryPoint(String deliveryPoint) {
		this.deliveryPoint = deliveryPoint;
	}

	// public Set<Food> getFavouriteList() {
	// return favouriteList;
	// }
	//
	// public void setFavouriteList(Set<Food> favouriteList) {
	// this.favouriteList = favouriteList;
	// }

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
