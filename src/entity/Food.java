package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Food {
	@Id
	private int foodId;
	private String name, description;
	private double price;
	private Date createDate;

}
