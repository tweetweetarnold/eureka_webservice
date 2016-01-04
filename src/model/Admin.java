package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents an Administrator entity model in the web application
 * 
 *
 */
@Entity
@Table(name = "admin")
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	private String username, password, name;
	private long contactNo;
	private Date createDate;
	
	/**
	 * Creates a default constructor for Admin
	 */
	public Admin() {
	}
	
	/**
	 * Creates a new Admin with a username, password, name and its contact number
	 * 
	 * @param username The Admin's username
	 * @param password The Admin's password
	 * @param name	The Admin's name
	 * @param contactNo The Admin's contact number
	 */
	public Admin(String username, String password, String name, long contactNo) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.contactNo = contactNo;
		this.createDate = new Date();
	}
	
	/**
	 * Retrieves the ID of the Admin
	 * 
	 * @return The Admin's ID
	 */
	public int getAdminId() {
		return adminId;
	}
	
	/**
	 * Changes the ID of the Admin
	 * 
	 * @param adminId The Admin's new ID
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	/**
	 * Retrieves the username of the Admin
	 * 
	 * @return The Admin's username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Changes the username of the Admin
	 * 
	 * @param username The Admin's new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Retrieves the password of the Admin
	 * 
	 * @return The Admin's password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Changes the current password with a new password
	 * 
	 * @param password The Admin's new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Retrieves the name of the Admin
	 * 
	 * @return The Admin's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Changes the name of the Admin
	 * 
	 * @param name The Admin's new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Retrieves the contact number of the Admin
	 * 
	 * @return The Admin's contact number
	 */
	public long getContactNo() {
		return contactNo;
	}
	
	/**
	 * Changes the contact number of the Admin with a new number
	 * 
	 * @param contactNo The Admin's new contact number
	 */
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	
	/**
	 * Retrieves the date of the Admin's entity model created
	 * 
	 * @return The date which the Admin's entity was created
	 */
	public Date getCreateDate() {
		return createDate;
	}
	
	/**
	 * Changes the date of the Admin's entity model created
	 * 
	 * @param createDate The new date of the Admin entity model being created
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
