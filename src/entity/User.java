/**
 * 
 */
package entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String username, password, name, bankAcc;
	private long contactNo;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "companyId")
	private Company company;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Food> favouriteList;
	private Date createDate;

	public User(String username, String password, String name, String bankAcc,
			long contactNo, Company company, Set<Food> favouriteList,
			Date createDate) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.bankAcc = bankAcc;
		this.contactNo = contactNo;
		this.company = company;
		this.favouriteList = favouriteList;
		this.createDate = createDate;
	}

	public Set<Food> getFavouriteList() {
		return favouriteList;
	}

	public void setFavouriteList(Set<Food> favouriteList) {
		this.favouriteList = favouriteList;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getBankAcc() {
		return bankAcc;
	}

	public void setBankAcc(String bankAcc) {
		this.bankAcc = bankAcc;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
