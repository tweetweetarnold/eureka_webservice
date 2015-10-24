/**
 * 
 */
package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	// private EmployeePK pk;
	@Column(unique = true)
	private String email;
	private String password, name;
	private double amountOwed;
	private long contactNo;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "companyId")
	private Company company;
//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private Set<Food> favouriteList;
	private String status;
	private Date createDate;

	public Employee() {
	}

	public Employee(String username, String password, String name, String email, long contactNo,
			Company company) {
		super();
		// this.pk = new EmployeePK(username, email);
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.amountOwed = 0;
		this.contactNo = contactNo;
		this.company = company;
		this.status = StringValues.EMPLOYEE_OK;
//		this.favouriteList = new HashSet<>();
		this.createDate = new Date();
	}

	// @Embeddable
	// public static class EmployeePK implements Serializable {
	//
	// private String username;
	// private String email;
	//
	// public EmployeePK() {
	// }
	//
	// public EmployeePK(String username, String email) {
	// this.username = username;
	// this.email = email;
	// }
	//
	// public String getUsername() {
	// return username;
	// }
	//
	// public void setUsername(String username) {
	// this.username = username;
	// }
	//
	// public String getEmail() {
	// return email;
	// }
	//
	// public void setEmail(String email) {
	// this.email = email;
	// }
	//
	// }

	public String getUsername() {
		// return pk.getUsername();
		return username;
	}

	public void setUsername(String username) {
		// pk.setUsername(username);
		this.username = username;
	}

	public String getEmail() {
		// return pk.getEmail();
		return email;
	}

	public void setEmail(String email) {
		// pk.setEmail(email);
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

//	public Set<Food> getFavouriteList() {
//		return favouriteList;
//	}
//
//	public void setFavouriteList(Set<Food> favouriteList) {
//		this.favouriteList = favouriteList;
//	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
