package model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyId;
	private String name;
	// @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy =
	// "company")
	// private Set<Employee> employeeList;
	private String companyCode;
	private Date createDate;
	private Date cutoffTime;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<Canteen> canteenList;

	public Company() {

	}

	public Company(String name, Date cutoffTime, Set<Canteen> canteenList,
			String companyCode) {
		super();
		this.name = name;
		// this.employeeList = new HashSet<>();
		this.createDate = new Date();
		this.cutoffTime = cutoffTime;
		this.canteenList = canteenList;
		this.companyCode = companyCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// public Set<Employee> getEmployeeList() {
	// return employeeList;
	// }
	//
	// public void setEmployeeList(Set<Employee> employeeList) {
	// this.employeeList = employeeList;
	// }

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCutoffTime() {
		return cutoffTime;
	}

	public void setCutoffTime(Date cutoffTime) {
		this.cutoffTime = cutoffTime;
	}

	public Set<Canteen> getCanteenList() {
		return canteenList;
	}

	public void setCanteenList(Set<Canteen> canteenList) {
		this.canteenList = canteenList;
	}

}
