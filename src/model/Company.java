package model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.joda.time.DateTime;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyId;
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	private Set<Employee> employeeList;
	private Date createDate;
	private Date cutoffTime;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Canteen> canteenList;

	public Company() {

	}

	public Company(String name, Set<Employee> employeeList, Date createDate,
			Date cutoffTime, Set<Canteen> canteenList) {
		super();
		this.name = name;
		this.employeeList = employeeList;
		this.createDate = createDate;
		this.cutoffTime = cutoffTime;
		this.canteenList = canteenList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(Set<Employee> employeeList) {
		this.employeeList = employeeList;
	}

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