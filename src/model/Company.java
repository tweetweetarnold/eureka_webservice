package model;

import java.util.Date;
import java.util.List;

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
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "company")
	private List<Employee> employeeList;
	private Date createDate;
	private Date cutoffTime;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Canteen> canteenList;

	public Company() {

	}

	public Company(String name, List<Employee> employeeList, Date createDate, Date cutoffTime,
			List<Canteen> canteenList) {
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

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
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

	public List<Canteen> getCanteenList() {
		return canteenList;
	}

	public void setCanteenList(List<Canteen> canteenList) {
		this.canteenList = canteenList;
	}

}
