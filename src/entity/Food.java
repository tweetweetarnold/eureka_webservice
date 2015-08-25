package entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodId;
	private String name;
	private String description;
	private double price;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hawkerId")
	private Hawker hawker;
	// private byte[] image;
	private Date createDate;

	public Food(String name, String description, double price, Hawker hawker,
			Date createDate) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.hawker = hawker;
		this.createDate = createDate;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Hawker getHawker() {
		return hawker;
	}

	public void setHawker(Hawker hawker) {
		this.hawker = hawker;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
