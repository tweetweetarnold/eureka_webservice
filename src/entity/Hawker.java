package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hawker {
	@Id
	private int hawkerId;
	private String username, password, name;
	private long contactNo;
	private Canteen canteen;
	private Date createDate;
	
	
	public Hawker(int hawkerId, String username, String password, String name,
			long contactNo, Canteen canteen, Date createDate) {
		super();
		this.hawkerId = hawkerId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.contactNo = contactNo;
		this.canteen = canteen;
		this.createDate = createDate;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	public int getHawkerId() {
		return hawkerId;
	}
	public void setHawkerId(int hawkerId) {
		this.hawkerId = hawkerId;
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
	public Canteen getCanteen() {
		return canteen;
	}
	public void setCanteen(Canteen canteen) {
		this.canteen = canteen;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

}
