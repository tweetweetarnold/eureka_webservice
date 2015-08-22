package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hawker {
	@Id
	private int hawkerId;
	private String username, password, name;
	private Date createDate;

}
