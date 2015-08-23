package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Driver {
	@Id
	private int driverId;
	private String username, password, name;
	private long contactNo;
	private Date createDate;
}
