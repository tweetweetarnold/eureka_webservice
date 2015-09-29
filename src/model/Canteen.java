package model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.javadocmd.simplelatlng.LatLng;

@Entity
public class Canteen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int canteenId;
	private String name;
	private String address;
//	private LatLng location;
	private Date createDate;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "canteen")
	private Set<Stall> hawkerList;
	
	
	
	public Canteen() {
		this.canteenId = 0;
		this.name = "";
		this.address = "";
//		this.location = null;
		this.createDate = null;
		this.hawkerList = null;
	}

	public Canteen(String name, String address, LatLng location,
			Date createDate, Set<Stall> hawkerList) {
		super();
		this.name = name;
		this.address = address;
//		this.location = location;
		this.createDate = createDate;
		this.hawkerList = hawkerList;
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

//	public LatLng getLocation() {
//		return location;
//	}
//
//	public void setLocation(LatLng location) {
//		this.location = location;
//	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Set<Stall> getHawkerList() {
		return hawkerList;
	}

	public void setHawkerList(Set<Stall> hawkerList) {
		this.hawkerList = hawkerList;
	}

}
