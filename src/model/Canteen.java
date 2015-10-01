package model;

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

@Entity
public class Canteen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int canteenId;
	private String name;
	private String address;
	private Date createDate;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "canteen")
	private Set<Stall> stallList;

	public Canteen() {
		this.canteenId = 0;
		this.name = "";
		this.address = "";
		this.createDate = null;
		this.stallList = null;
	}

	public Canteen(String name, String address, Date createDate,
			Set<Stall> stallList) {
		super();
		this.name = name;
		this.address = address;
		this.createDate = createDate;
		this.stallList = stallList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Set<Stall> getStallList() {
		return stallList;
	}

	public void setStallList(Set<Stall> stallList) {
		this.stallList = stallList;
	}

}