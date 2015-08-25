package entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyId;
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	private Set<User> userList;
	private Date createDate;
	

	public Company(String name, Set<User> userList, Date createDate) {
		super();
		this.name = name;
		this.userList = userList;
		this.createDate = createDate;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUserList() {
		return userList;
	}

	public void setUserList(Set<User> userList) {
		this.userList = userList;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
