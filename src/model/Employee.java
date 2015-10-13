/**
 * 
 */
package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import value.StringValues;

@Entity
@Table(name = "employee")
public class Employee {

	// @EmbeddedId
	@Id
	private String username;
	private String password, name;
	private long creditCardNo;
	private double amountOwed;
	private long contactNo;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "companyId")
	private Company company;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Food> favouriteList;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<FoodOrder> orderHistory;
	private String status;
	private Date createDate;

	public Employee() {
	}

	public Employee(String username, String password, String name, long creditCardNo,
			long contactNo, Company company, Date createDate) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.creditCardNo = creditCardNo;
		this.amountOwed = 0;
		this.contactNo = contactNo;
		this.company = company;
		this.status = StringValues.EMPLOYEE_OK;
		this.favouriteList = new HashSet<>();
		this.orderHistory = new HashSet<>();
		this.createDate = createDate;
	}

	// @Embeddable
	// public static class EmployeePK implements Serializable {
	//
	// private String username;
	//
	// public EmployeePK() {
	// }
	//
	// public EmployeePK(String username) {
	// this.username = username;
	// }
	//
	// public String getUsername() {
	// return username;
	// }
	//
	// public void setUsername(String username) {
	// this.username = username;
	// }
	// }

	public String getUsername() {
		return username;
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

	public void setUsername(String username) {
		this.username = username;
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

	public long getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(long creditCardNo) {
		this.creditCardNo = creditCardNo;
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

	public Set<Food> getFavouriteList() {
		return favouriteList;
	}

	public void setFavouriteList(Set<Food> favouriteList) {
		this.favouriteList = favouriteList;
	}

	public Set<FoodOrder> getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(Set<FoodOrder> orderHistory) {
		this.orderHistory = orderHistory;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
