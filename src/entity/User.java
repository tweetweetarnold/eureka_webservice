/**
 * 
 */
package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author Arnold. User class
 *
 */
@Entity
public class User {
	@Id  @GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String username, password, name;
	private Date createDate;

	public User(String username, String password, String name,
			Date createDate) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.createDate = createDate;
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
