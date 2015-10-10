/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

	@EmbeddedId
	private EmployeePK pk;
	private String password, name;
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

	public Employee(String username, String password, String name, long creditCardNo,
			long eDollars, long contactNo, Company company, Set<Food> favouriteList,
			Set<FoodOrder> orderHistory, Date createDate) {
		super();
		this.pk = new EmployeePK(username);
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

	@Embeddable
	public static class EmployeePK implements Serializable {

		private String username;

		public EmployeePK() {
		}

		public EmployeePK(String username) {
			this.username = username;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

	}

	public String getUsername() {
		return pk.getUsername();
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
