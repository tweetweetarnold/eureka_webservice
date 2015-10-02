/**
 * 
 */
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

/**
 * @author Arnold. User class
 * 
 */
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	private String username, password, name;
	private long creditCardNo;
	private long eDollars;
	private long contactNo;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "companyId")
	private Company company;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Food> favouriteList;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<FoodOrder> orderHistory;
	private Date createDate;

	public Employee() {

	}

	public Employee(String username, String password, String name,
			long creditCardNo, long eDollars, long contactNo, Company company,
			Set<Food> favouriteList, Set<FoodOrder> orderHistory,
			Date createDate) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.creditCardNo = creditCardNo;
		this.eDollars = eDollars;
		this.contactNo = contactNo;
		this.company = company;
		this.favouriteList = favouriteList;
		this.orderHistory = orderHistory;
		this.createDate = createDate;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public long geteDollars() {
		return eDollars;
	}

	public void seteDollars(long eDollars) {
		this.eDollars = eDollars;
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
