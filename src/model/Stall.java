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
import javax.persistence.Table;

@Entity
@Table(name = "stall")
public class Stall {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stallId;
	private String name;
	private long contactNo;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "canteenId")
	private Canteen canteen;
	private Date createDate;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "stall")
	private Set<Food> foodList;

	public Stall() {
	}

	public Stall(String name, long contactNo, Canteen canteen, Set<Food> foodList) {
		super();
		this.name = name;
		this.contactNo = contactNo;
		this.canteen = canteen;
		this.createDate = new Date();
		this.foodList = foodList;
	}

	public int getStallId() {
		return stallId;
	}

	public void setStallId(int stallId) {
		this.stallId = stallId;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Canteen getCanteen() {
		return canteen;
	}

	public void setCanteen(Canteen canteen) {
		this.canteen = canteen;
	}

	public Set<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(Set<Food> foodList) {
		this.foodList = foodList;
	}

}
